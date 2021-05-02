package com.example.customer

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Application.registerCustomerRoute() {
    routing {
        customerRouting()
    }
}

fun Route.customerRouting() {

    val service = CustomerService

    route("/") {
        get {
            call.respond(service.findAll())
        }
        post {
            val person = call.receive<Person>()
            service.store(person)
            call.respond(HttpStatusCode.Created, "Customer created")
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
