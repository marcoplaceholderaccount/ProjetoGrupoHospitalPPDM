package com.example.projetogrupohospital

class Consulta(
               val codigoconsulta: String,
               var nomepaciente: String,
               var nomemedico : String,
               var nomeconsultorio: String,
               var dataconsulta : String
) {
    override fun toString():String{
       return "Codigo de consulta: $codigoconsulta  Nome do Paciente:$nomepaciente  Nomde do Medico: $nomemedico"
    }
}