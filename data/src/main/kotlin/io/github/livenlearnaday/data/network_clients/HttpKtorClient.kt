package io.github.livenlearnaday.data.network_clients

import io.github.livenlearnaday.data.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import timber.log.Timber

class HttpKtorClient {

    fun build(): HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                }
            )
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Timber.d(message)
                }
            }

            if (BuildConfig.DEBUG) {
                this.level = LogLevel.ALL
            } else {
                this.level = LogLevel.NONE
            }
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }
}
