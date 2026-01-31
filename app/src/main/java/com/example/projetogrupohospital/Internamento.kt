package com.example.projetogrupohospital

class Internamento(
    var codigo: String,
    var paciente: Paciente,
    var enfermaria: Enfermaria,
    var dataEnt : String,
    var dataSaid : String,
    var estado : Boolean
)
{
    override fun toString(): String {
        return "Codigo: $codigo Paciente: ${paciente.nome} Quarto: ${enfermaria.nome}"
    }

}