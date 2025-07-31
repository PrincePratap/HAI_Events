package com.cody.haievents.common.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val BASE_URL = "https://haievents.com"

internal abstract class KtorApi {



    val client = HttpClient {
        // 1️⃣ Install Logging first so we can see the raw bytes
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println("📡 Ktor ▶ $message")
                }
            }
            level                   = LogLevel.ALL
//            alwaysLogRequestBody    = true
//            alwaysLogResponseBody   = true
        }

        // 2️⃣ Then install ContentNegotiation
        install(ContentNegotiation) {
            json(Json {
                prettyPrint       = true
                isLenient         = true
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    fun HttpRequestBuilder.endPoint(path: String){
        url {
            takeFrom(BASE_URL)
            path(path)
            contentType(ContentType.Application.Json)
        }
    }

    fun HttpRequestBuilder.setToken(token: String) {
        headers {
            append(name = "Authorization", value = "Bearer $token")
        }
    }

    fun HttpRequestBuilder.setupMultipartRequest() {
        contentType(ContentType.MultiPart.FormData)
    }
}