package edu.cnm.deepdive.skywatch.app.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
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

    @Query(OAUTH_KEY_QUERY)
    suspend fun select(oauthKey: String): User?

}

private const val USER_ID_QUERY = """
    SELECT
        *
    FROM
        user
    WHERE
        user_id = :id
"""

private const val OAUTH_KEY_QUERY = """
    SELECT 
        * 
    FROM 
        user
    WHERE
        oauth_key = :oauthKey
"""