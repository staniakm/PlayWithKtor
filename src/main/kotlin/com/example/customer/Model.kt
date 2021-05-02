package com.example.customer

import kotlinx.serialization.Serializable

@Serializable
data class Person(var id: Int?=-1, val name: String, val surname: String)

@Serializable
data class ErrorResponse(val code: String, val description: String)
