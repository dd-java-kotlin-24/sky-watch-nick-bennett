package edu.cnm.deepdive.skywatch.app.service

import androidx.lifecycle.LiveData
import edu.cnm.deepdive.skywatch.app.model.entity.Favorite
import edu.cnm.deepdive.skywatch.app.repository.FavoritesRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.future.future
import java.util.concurrent.CompletableFuture

@Singleton
class FavoritesServiceImpl @Inject constructor(private val repository: FavoritesRepository) :
    FavoritesService {

    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun delete(favorite: Favorite): CompletableFuture<Void?> =
        scope.future {
            repository.delete(favorite)
            null
        }

    override fun insert(favorite: Favorite): CompletableFuture<Favorite> =
        scope.future {
            favorite.copy(id = repository.insert(favorite))
        }

    override fun selectOrderByDescription(userId: Long?): LiveData<List<Favorite>> =
        repository.selectOrderByDescription(userId)

    override fun selectOrderByFavorited(userId: Long?): LiveData<List<Favorite>> =
        repository.selectOrderByFavorited(userId)

}