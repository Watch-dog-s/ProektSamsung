package com.example.myapplication.Ktor

import io.ktor.*
import io.ktor.client.HttpClient
import io.ktor.http.ContentType.Application.Json
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*

import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json



object KtorClient {
    val instance by lazy{
        HttpClient(Android){
            install(ContentNegotiation)
            {
                json(Json{
                    ignoreUnknownKeys =true
                    isLenient = true
                })
            }
        }

    }
}