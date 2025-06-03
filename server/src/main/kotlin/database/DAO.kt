package com.example.database

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction


interface UserDao{
    fun getUserByEmail (login: String): user?
    fun createUser (login: String, password: String, type:String): user
}



class UserDaoImpl : UserDao {


    override fun getUserByEmail(login: String): user? {
        return transaction {
            users.select() { users.login eq login }
                .singleOrNull()
                ?.let {
                    user(
                        id = it[users.id],
                        login = it[users.login],
                        password = it[users.password]
                    )
                }
        }
    }



    override fun createUser(login: String, password: String,type:String): user {
        return transaction {
            val id = users.insert {
                it[users.login] = login
                it[users.password] = password
            }.get(users.id)

            user(id, login, password)
        }
    }
}