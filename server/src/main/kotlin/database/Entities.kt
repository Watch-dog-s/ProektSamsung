package com.example.database

import org.jetbrains.exposed.sql.Table

object users: Table("\"user\""){
    val id = integer("id").autoIncrement()
    val login = varchar("login", 50)
    val password = varchar("password", 50)


    override val primaryKey=PrimaryKey(id)
}

data class user(
    val id: Int,
    val login: String,
    val password: String,

)