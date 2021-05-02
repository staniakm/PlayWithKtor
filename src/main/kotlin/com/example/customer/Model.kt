package com.example.customer

import kotlinx.serialization.Serializable

@Serializable
data class Person(var id: Int? = -1, val name: String, val surname: String)

@Serializable
data class ErrorResponse(val code: String, val description: String)

@Serializable
data class PersonDTO(val name: String, val surname: String) {

    companion object {
        fun toEntity(dto: PersonDTO): Person {
            return Person(-1, dto.name, dto.surname)
        }

        fun toDto(person: Person) =
            PersonDTO(person.name, person.surname)

    }
}