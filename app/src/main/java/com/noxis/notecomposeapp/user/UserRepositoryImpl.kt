package com.noxis.notecomposeapp.user

class UserRepositoryImpl(
    private val dataSource: DataSource
): UserRepository {

    override suspend fun saveUser(user: User) {
        dataSource.save(user)
    }

    override suspend fun getUser(id: String): User {
        return dataSource.get(id) ?: throw IllegalArgumentException("User with id $id not found")
    }

    override suspend fun deleteUser(id: String) {
        dataSource.clear(id)
    }


}