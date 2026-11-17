package edu.cnm.deepdive.skywatch.app.repository

import androidx.lifecycle.LiveData
import edu.cnm.deepdive.skywatch.app.model.dao.UserDao
import edu.cnm.deepdive.skywatch.app.model.entity.User
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.net.URL
import kotlin.time.Clock

@Singleton
class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun insert(user: User): Long =
        userDao.insert(user)

    suspend fun update(user: User): Int =
        userDao.update(user)

    suspend fun delete(user: User): Int =
        userDao.delete(user)

    fun select(userId: Long): LiveData<User> =
        userDao.select(userId)

    @JvmOverloads
    suspend fun selectOrCreate(oauthKey: String, displayName: String, avatar: URL? = null): User {
        var user = userDao.select(oauthKey)
        if (user == null) {
            user = User(
                oauthKey = oauthKey,
                displayName = displayName,
                avatar = avatar,
                created = Clock.System.now()
            )
            val userId = userDao.insert(user)
            user = user.copy(id = userId)
        }
        return user
    }
}