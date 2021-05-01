package com.example

import com.example.customer.registerCustomerRoute
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    registerCustomerRoute()
}

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}
