package edu.cnm.deepdive.skywatch.app.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import edu.cnm.deepdive.skywatch.app.model.entity.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User): Long

    @Update
    suspend fun update(user: User): Int

    @Delete
    suspend fun delete(user: User): Int

    @Query(USER_ID_QUERY)
    fun select(id: Long): LiveData<User>

}

private const val USER_ID_QUERY = """
    SELECT
        *
    FROM
        user
    WHERE
        user_id = :id
"""
