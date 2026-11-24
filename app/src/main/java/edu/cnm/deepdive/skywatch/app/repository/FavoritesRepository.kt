package edu.cnm.deepdive.skywatch.app.repository

import androidx.lifecycle.LiveData
import edu.cnm.deepdive.skywatch.app.model.entity.Favorite

interface FavoritesRepository {

    suspend fun insert(favorite: Favorite): Long

    suspend fun delete(favorite: Favorite): Int

    fun selectOrderByDescription(userId: Long? = null): LiveData<List<Favorite>>

    fun selectOrderByFavorited(userId: Long? = null): LiveData<List<Favorite>>

}