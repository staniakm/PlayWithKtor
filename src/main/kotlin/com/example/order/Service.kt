package com.example.order


object OrderService {
    private val database = mapOf(1 to listOf(Order(1, 1, 10)))

    fun findOrders(customerId: Int): List<Order>? {
        return database[customerId]
    }
}