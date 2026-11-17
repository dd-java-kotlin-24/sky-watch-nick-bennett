## Background

In the app subproject, I have two persistent entity classes, `User.kt` and `Favorite.kt`. Every favorite is associated with a user, and a user may have zero or more favorites.

I also have two DAOs, one for each entity class.

I want to have the ability for the current user to be exposed through a service, usable in viewmodels and other services.

Currently most code in the project is in Kotlin; however, I want the flexibility to write viewmodels and/or UI controllers in Java.

## Task

Give me recommendations for creating a user repository, favorite repository, and a service layer (if appropriate) that will enable the above.
