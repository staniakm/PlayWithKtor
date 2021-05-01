package com.example.order

import kotlinx.serialization.Serializable

@Serializable
data class Order(val id: Int, val customerId: Int, val price: Int)
