package edu.cnm.deepdive.skywatch.app.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import edu.cnm.deepdive.skywatch.app.model.entity.Favorite

@Dao
interface FavoriteDao {

    @Insert
    suspend fun insert(favorite: Favorite): Long

    @Delete
    suspend fun delete(favorite: Favorite): Int

    @Query(ORDER_BY_DESCRIPTION)
    fun selectOrderByDescription(userId: Long? = null): LiveData<List<Favorite>>

    @Query(ORDER_BY_FAVORITED)
    fun selectOrderByFavorited(userId: Long? = null): LiveData<List<Favorite>>
}

private const val ORDER_BY_DESCRIPTION = """
    SELECT 
        * 
    FROM 
        favorite 
    WHERE 
        (:userId IS NOT NULL AND user_id = :userId)
        OR (:userId IS NULL AND user_id IS NULL)
    ORDER BY 
        description ASC
"""

private const val ORDER_BY_FAVORITED = """
    SELECT 
        * 
    FROM 
        favorite 
    WHERE 
        (:userId IS NOT NULL AND user_id = :userId)
        OR (:userId IS NULL AND user_id IS NULL)
    ORDER BY 
        favorited DESC
"""