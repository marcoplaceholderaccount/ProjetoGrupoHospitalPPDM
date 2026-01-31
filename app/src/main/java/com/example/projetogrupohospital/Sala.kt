package com.example.projetogrupohospital

open abstract class Sala(
    val codigo : String,
    var nome : String,
    var tipo : String,
    var quantidade : Int
) {

    override fun toString(): String {
        return "Codigo: $codigo Nome: $nome"
    }
}