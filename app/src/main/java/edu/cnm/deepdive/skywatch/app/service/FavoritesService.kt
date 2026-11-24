package edu.cnm.deepdive.skywatch.app.service

import androidx.lifecycle.LiveData
import edu.cnm.deepdive.skywatch.app.model.entity.Favorite
import java.util.concurrent.CompletableFuture

interface FavoritesService {

    fun delete(favorite: Favorite): CompletableFuture<Void?>

    fun insert(favorite: Favorite): CompletableFuture<Favorite>

    fun selectOrderByDescription(userId: Long?): LiveData<List<Favorite>>

    fun selectOrderByFavorited(userId: Long?): LiveData<List<Favorite>>

}