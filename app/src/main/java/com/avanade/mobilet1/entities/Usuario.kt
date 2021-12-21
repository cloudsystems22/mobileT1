package com.avanade.mobilet1.entities

data class Usuario(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = ""
) {
    override fun toString(): String {
        return """nome: $firstName, 
                  sobrenome: $lastName, 
                  email: $email
                  """.trimIndent()
    }
}