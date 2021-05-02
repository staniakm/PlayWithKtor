package com.example.customer

import com.example.order.Order
import com.example.order.OrderService

private object CustomerRepository {
    private val customers = mutableListOf<Person>()

    init {
        with(customers) {
            add(Person(1, "Jan", "KOwalski"))
            add(Person(2, "Jan", "Nowak"))
            add(Person(3, "Jan", "Wańkowski"))
        }
    }

    fun findById(id: Int?): Person? {
        return customers.find { it.id == id }
    }

    fun findAll(): List<Person> {
        return customers.toList()
    }

    fun create(person: Person) {
        val id = customers.maxOf { it.id!! } + 1
        person.apply {
            this.id = id
        }
        customers.add(person)
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
