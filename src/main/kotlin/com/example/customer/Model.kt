package com.example.customer

import kotlinx.serialization.Serializable

@Serializable
data class Person(val id: Int, val name: String, val surname: String)

@Serializable
data class ErrorResponse(val code: String, val description: String)
