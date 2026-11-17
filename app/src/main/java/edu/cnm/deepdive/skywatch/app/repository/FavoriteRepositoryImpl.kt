package edu.cnm.deepdive.skywatch.app.repository

import androidx.lifecycle.LiveData
import edu.cnm.deepdive.skywatch.app.model.dao.FavoriteDao
import edu.cnm.deepdive.skywatch.app.model.entity.Favorite
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class FavoriteRepositoryImpl @Inject constructor(private val favoriteDao: FavoriteDao) :
    FavoriteRepository {

    override suspend fun insert(favorite: Favorite): Long =
        favoriteDao.insert(favorite)

    override suspend fun delete(favorite: Favorite): Int =
        favoriteDao.delete(favorite)

    override fun selectOrderByDescription(userId: Long): LiveData<List<Favorite>> =
        favoriteDao.selectOrderByDescription(userId)

    override fun selectOrderByFavorited(userId: Long): LiveData<List<Favorite>> =
        favoriteDao.selectOrderByFavorited(userId)
}