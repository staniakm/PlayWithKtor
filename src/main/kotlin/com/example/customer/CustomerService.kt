package com.example.customer

import com.example.order.Order
import com.example.order.OrderService

private object CustomerRepository {
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

    fun create(person: Person) {
        val id = customers.maxOf { it.key } + 1
        person.apply {
            this.id = id
        }
        customers[id] = person
    }
}

object CustomerService {

    private val repo = CustomerRepository
    private val service = OrderService

    fun findById(id: Int?) =
        repo.findById(id)
            ?.let {
                PersonDTO.toDto(it)
            }


    fun findAll() = repo.findAll()

    fun findOrders(id: Int?): List<Order>? {
        return repo.findById(id)?.let {
            service.findOrders(it.id!!)
        }
    }

    fun store(personDTO: PersonDTO) {
        repo.create(PersonDTO.toEntity(personDTO))
    }
}
