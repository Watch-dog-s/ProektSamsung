package com.example.database

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.transaction
import kotlinx.coroutines.withContext


//Позволяет выполнять операции с БД в suspend функциях
suspend fun <T> dbQuery(block: () -> T): T =
    withContext(Dispatchers.IO) {
        transaction { block() }
    }
