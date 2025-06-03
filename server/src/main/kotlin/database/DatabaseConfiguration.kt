package com.example.database

import com.example.authorization.LoginRouting
import jdk.internal.net.http.common.Log
import org.jetbrains.exposed.sql.Database

object DatabaseConfiguration
{
    fun init(){
        Database.connect(
            url="jdbc:postgresql://localhost:5432/Samsung",
            driver="org.postgresql.Driver",
            user = "postgres",
            password = "12345"
        )
    }
}