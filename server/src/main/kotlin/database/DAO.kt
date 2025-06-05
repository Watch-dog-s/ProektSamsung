package com.example.database

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import com.example.API.students

interface UserDao{
    suspend fun getUserByEmail (login: String): UserWithGroupInfo?

}


class UserDaoImpl : UserDao {
    override suspend fun getUserByEmail(login: String): UserWithGroupInfo? = dbQuery {
        (users innerJoin students).select {
            users.login eq login
        }.mapNotNull {
            UserWithGroupInfo(
                id = it[users.id],
                login = it[users.login],
                studentId = it[users.studentId],
                groupId = it[students.groupId],
                password = it[users.password]
            )
        }.singleOrNull()
    }
}