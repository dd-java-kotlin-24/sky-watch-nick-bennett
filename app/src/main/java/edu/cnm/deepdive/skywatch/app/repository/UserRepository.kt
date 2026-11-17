package edu.cnm.deepdive.skywatch.app.repository

import androidx.lifecycle.LiveData
import edu.cnm.deepdive.skywatch.app.model.entity.User
import java.net.URL

interface UserRepository {
    suspend fun insert(user: User): Long

    suspend fun update(user: User): Int

    suspend fun delete(user: User): Int
    fun select(userId: Long): LiveData<User>

    suspend fun selectOrCreate(oauthKey: String, displayName: String, avatar: URL? = null): User
}