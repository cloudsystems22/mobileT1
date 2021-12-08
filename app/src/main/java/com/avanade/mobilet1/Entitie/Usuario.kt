package com.avanade.mobilet1.Entitie

import java.util.*

class Usuario constructor(nome: String? = null, email: String? = null, sobreNome: String? = null) {

    lateinit var nome: String
    lateinit var email: String
    lateinit var sobreNome: String

    init {
        if (nome != null) {
            this.nome = nome
        }
        if (email != null) {
            this.email = email
        }
        if (sobreNome != null) {
            this.sobreNome = sobreNome
        }
    }

    override fun toString(): String {
        return """
            nome: $nome 
            email: $email
            SobreNome: $sobreNome""".trimIndent()

    }
}