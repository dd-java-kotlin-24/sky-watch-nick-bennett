I recommend **two repositories and a small application-scoped session service**, following the coroutine-to-`CompletableFuture` pattern already used by your `MediaSearchService`.

| Component | Responsibility |
|---|---|
| `UserRepository` | Find, insert, update, and observe persisted users through `UserDao`. |
| `FavoriteRepository` | Read and modify favorites for an explicit user ID through `FavoriteDao`. |
| `UserSessionService` | Own the current signed-in user state and coordinate account selection and sign-out. |

Keep these in `app`: their dependencies on Room entities and Android observable types make that the appropriate location.

**Repositories**

Keep write operations and one-time lookups as Kotlin `suspend` functions. For example, `UserRepository` could expose `findByOauthKey`, `create`, and `update`; `FavoriteRepository` could expose `add`, `remove`, and the two existing sorted queries.

Repositories should receive explicit IDs rather than discover the current user themselves. This keeps persistence reusable and prevents session state from becoming entangled with database access.

When inserting a `User`, use the ID returned by `UserDao.insert` to produce `user.copy(id = generatedId)`. Room does not update the immutable entity’s `id` property for you.

**Current-user service**

Make `UserSessionService` a singleton with constructor injection. Its responsibilities would be:

- Resolve a verified authentication identity to a local user using `oauthKey`.
- Create that local user when appropriate.
- Publish the selected user and subsequent changes to that user’s database row.
- Clear session state on sign-out without deleting the persisted user or favorites.

Expose read-only state; keep mutation private. Represent initialization separately from signed-out state so consumers can distinguish “still restoring the session” from “no user.”

A Room row alone does not establish who is authenticated. Your authentication dependencies are currently commented out, so choosing and integrating an identity provider remains a separate decision. Do not treat a caller-supplied OAuth key as proof of authentication.

**Java interoperability**

Use this boundary:

| Operation | Java-facing API |
|---|---|
| Observe current user or favorites | `LiveData<…>` |
| Execute a one-time asynchronous operation | `CompletableFuture<T>` |

This fits your existing Java `SearchViewModel`. A small service facade can wrap repository calls in `scope.future { … }`, just as `MediaSearchServiceImpl` does. That bridge runs without blocking the caller and connects future cancellation to coroutine cancellation. [Kotlin documentation](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.future/future.html)

Give the coroutine scope an explicit owner, preferably an injected application scope for singleton services. Java callers should handle exceptional completion and cancel screen-specific work when appropriate. Avoid calling `get()` or `join()` on the UI thread.

For your current project, passing the existing DAO `LiveData` through the repositories is a reasonable small-step compromise. Longer term, I would use `Flow` inside repositories and services and adapt to `LiveData` in ViewModels. Android recommends keeping `LiveData` near the UI rather than using it for data-layer stream processing. [Android guidance](https://developer.android.com/topic/libraries/architecture/livedata)

**Favorites and account changes**

Keep `FavoriteRepository` independent of `UserSessionService`. If multiple ViewModels need “favorites for the current user,” add a small `CurrentUserFavoritesService` that depends on both.

That service should switch its observed query when the user changes and publish an empty list when signed out. For writes, capture the current persisted user ID when the operation starts. Validate ownership before deleting a favorite; the foreign key alone does not prevent one user’s operation from deleting another user’s row.

Two existing declarations deserve attention before implementation:

- `Favorite.externalKey` is globally unique. That prevents two users from favoriting the same external item. If favorites belong independently to each user, recommend uniqueness on **`(user_id, external_key)`**, subject to assignment constraints and migration requirements.
- `UserDao.select(id)` returns `LiveData<User>`, although a missing or deleted row is possible. Where declaration changes are permitted, `LiveData<User?>` would express that accurately.

Finally, the existing `UserRepository` has `@Singleton` but no injectable constructor. Recommend adding constructor injection or providing it through a Hilt module; the scope annotation alone does not supply a construction binding.

No files were changed and no tests were run, since this task requests recommendations only. Your existing staged and unstaged changes were left untouched.
