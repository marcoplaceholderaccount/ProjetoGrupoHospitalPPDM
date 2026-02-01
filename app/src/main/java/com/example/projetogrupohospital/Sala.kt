package com.example.projetogrupohospital

open class Sala(
    var codigo: String = "",
    var nome: String = "",
    var tipo: String = "",
    var quantidade: Int = 0
) {
    override fun toString(): String {
        return "$codigo - $nome"
    }
}
