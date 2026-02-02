package com.example.projetogrupohospital

open class Profissional(
    var codigo: String = "",
    var nome: String = "",
    var contacto: String = "",
    var turno: String = "",
    var tipo: String = ""
) {
    override fun toString(): String {
        return "$codigo - $nome"
    }
}
