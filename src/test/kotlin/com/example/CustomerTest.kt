package com.example

import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class CustomerTest {

    @Test
    fun testGetOrder() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(
                    """[{"id":1,"name":"Jan","surname":"KOwalski"},{"id":2,"name":"Jan","surname":"Nowak"},{"id":3,"name":"Jan","surname":"Wańkowski"}]""",
                    response.content
                )
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }
}