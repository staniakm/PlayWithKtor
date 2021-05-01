package com.example.plugins

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*


fun Application.registerCustomerRoute() {
    routing {
        customerRouting()
    }
}

fun Route.customerRouting() {

    val service = Service

    route("/") {
        get {
            call.respond(service.findAll())
        }
        route("{id}") {
            get {
                call.respond(
                    service.findById(call.parameters["id"]?.toInt()) ?: return@get call.respond(
                        HttpStatusCode.NotFound,
                        ErrorResponse(
                            HttpStatusCode.NotFound.description,
                            "Customer not found"
                        )
                    )
                )
            }
            get("/orders") {
                call.respond(
                    service.findOrders(call.parameters["id"]?.toInt()) ?: return@get call.respond(
                        HttpStatusCode.NotFound,
                        ErrorResponse(
                            HttpStatusCode.NotFound.description,
                            "Customer not found"
                        )
                    )
                )
            }
        }
    }
}
