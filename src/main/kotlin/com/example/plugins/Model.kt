package com.example.plugins

import kotlinx.serialization.Serializable


@Serializable
data class Order(val id: Int, val customerId: Int, val price: Int)

@Serializable
data class Person(val id: Int, val name: String, val surname: String)

@Serializable
data class ErrorResponse(val code: String, val description: String)
