package com.example.projetogrupohospital

class Paciente (
    var nome: String,
    var id: String,
    var datanasc: String,
    var contato: String,
    var sexo:String,
    var endereco:String
){

    override fun toString(): String {
        return "Nome: $nome ID: $id Sexo:$sexo"
    }


}



