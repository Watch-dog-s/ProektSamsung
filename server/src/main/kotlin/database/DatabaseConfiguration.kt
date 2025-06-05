package com.example.database

import com.example.authorization.LoginRouting
import jdk.internal.net.http.common.Log
import org.jetbrains.exposed.sql.Database

object DatabaseConfiguration
{
    lateinit var db: Database
        private set

    fun init(){
        db= Database.connect(
            url="jdbc:postgresql://localhost:5432/Samsung",
            driver="org.postgresql.Driver",
            user = "postgres",
            password = "12345"
        )
    }
}