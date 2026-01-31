package com.example.projetogrupohospital

class Enfermaria(
    codigo : String,
    nome : String,
    tipo : String,
    quantidade : Int,
    var listainternamentos : MutableList<Internamento> = mutableListOf()
) : Sala(codigo = codigo, nome = nome, tipo = tipo, quantidade = quantidade) {
}