package edu.cnm.deepdive.skywatch.app.repository

import androidx.lifecycle.LiveData
import edu.cnm.deepdive.skywatch.app.model.dao.UserDao
import edu.cnm.deepdive.skywatch.app.model.entity.User
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.net.URL
import kotlin.time.Clock

@Singleton
class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {

    override suspend fun insert(user: User): Long =
        userDao.insert(user)

    override suspend fun update(user: User): Int =
        userDao.update(user)

    override suspend fun delete(user: User): Int =
        userDao.delete(user)

    override fun select(userId: Long): LiveData<User> =
        userDao.select(userId)

    override suspend fun selectOrCreate(oauthKey: String, displayName: String, avatar: URL?): User =
        userDao.select(oauthKey) ?: create(oauthKey, displayName, avatar)

    private suspend fun create(oauthKey: String, displayName: String, avatar: URL?): User =
        User(
            oauthKey = oauthKey,
            displayName = displayName,
            avatar = avatar,
            created = Clock.System.now()
        ).let { user -> user.copy(id = userDao.insert(user)) }

}