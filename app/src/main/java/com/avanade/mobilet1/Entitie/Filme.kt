package com.avanade.mobilet1.Entitie

import java.util.*

class Filme constructor(
    nome: String? = null,
    sinopse: String? = null,
    dataDeLancamento: String? = null,
    diretor: String? = null,
    genero: Genero? = null,
    roteiro: String? = null,
    idioma: String? = null,
    tituloNoBrasil: String? = null,
    premios: String? = null,
    curiosidades: String? = null,
    distribuicao: String? = null,
    producao: String? = null
) {

    lateinit var nome: String
    lateinit var sinopse: String
    lateinit var dataDeLancamento: String
    lateinit var diretor: String
    lateinit var genero: Genero
    lateinit var roteiro: String
    lateinit var idioma: String
    lateinit var tituloNoBrasil: String
    lateinit var premios: String
    lateinit var curiosidades: String
    lateinit var distribuicao: String
    lateinit var producao: String

    init {
        if (nome != null) {
            this.nome = nome
        }
        if (sinopse != null) {
            this.sinopse = sinopse
        }
        if (dataDeLancamento != null) {
            this.dataDeLancamento = dataDeLancamento
        }
        if (diretor != null) {
            this.diretor = diretor
        }
        if (genero != null) {
            this.genero = genero
        }
        if (roteiro != null) {
            this.roteiro = roteiro
        }
        if (idioma != null) {
            this.idioma = idioma
        }
        if (tituloNoBrasil != null) {
            this.tituloNoBrasil = tituloNoBrasil
        }
        if (premios != null) {
            this.premios = premios
        }
        if (curiosidades != null) {
            this.curiosidades = curiosidades
        }
        if (distribuicao!= null){
            this.distribuicao = distribuicao
        }
        if (producao!= null){
            this.producao = producao
        }
    }

    fun carregarAtributos(): MutableList<Array<Genero>> {
        return Arrays.asList(Genero.values())
    }

    override fun toString(): String {
        return """nome: $nome, 
                  sinopse: $sinopse, 
                  dataDeLancamento: $dataDeLancamento, 
                  diretor: $diretor, 
                  genero: $genero, 
                  roteiro: $roteiro, 
                  idioma: $idioma, 
                  tituloNoBrasil: $tituloNoBrasil, 
                  premios: $premios, 
                  curiosidades: $curiosidades,
                  Distribuição: $distribuicao,
                  Produção: $producao""".trimIndent()
    }
}

