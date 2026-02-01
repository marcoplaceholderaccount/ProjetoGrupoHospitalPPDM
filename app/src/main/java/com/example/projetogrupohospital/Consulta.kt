package com.example.projetogrupohospital

class Consulta(
               val codigoconsulta: String = "",
               var pacienteId: String = "",
               var medicoCodigo : String = "",
               var consultorioCodigo : String = "",
               var data : String = ""
)
{

    override fun toString(): String {
        return "Consulta: $codigoconsulta | Paciente: $pacienteId | Médico: $medicoCodigo | Consultório: $consultorioCodigo | Data: $data"
    }

}