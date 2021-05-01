package com.example.plugins

private object Repository {
    private val customers = mutableMapOf<Int, Person>()

    init {
        customers[1] = Person(1, "Jan", "KOwalski")
        customers[2] = Person(2, "Jan", "Nowak")
        customers[3] = Person(3, "Jan", "Wańkowski")
    }

    fun findById(id: Int?): Person? {
        return customers[id]
    }

    fun findAll(): List<Person> {
        return customers.map { it.value }.toList()
    }
}

object Service2 {
    private val database = mapOf(1 to listOf(Order(1, 1, 10)))

    fun findOrders(customerId: Int): List<Order> {
        return database.getOrDefault(customerId, listOf())
    }
}

object Service {

    private val repo = Repository
    private val service = Service2

    fun findById(id: Int?) =
        repo.findById(id)

    fun findAll() = repo.findAll()

    fun findOrders(id: Int?): List<Order> {
        return findById(id)?.let {
            service.findOrders(it.id)
        }.let {
            if (it.isNullOrEmpty()) listOf() else it
        }
    }
}
