package com.example.projetogrupohospital

class Internamento(
    var codigo: String = "",
    var pacienteId: String = "",
    var enfermariaCodigo: String = "",
    var dataEnt : String = "",
    var dataSaid : String = "",
    var estado : Boolean = true
)
{
    override fun toString(): String {
        // Procura o nome do paciente e o nome da enfermaria nas listas globais.
        val nomePaciente = ListaGlobal.listapacientes.find { it.id == pacienteId }?.nome ?: pacienteId
        val nomeEnfermaria = ListaGlobal.listasalas
            .filterIsInstance<Enfermaria>()
            .find { it.codigo == enfermariaCodigo }?.nome ?: enfermariaCodigo

        return "Código: $codigo  Paciente: $nomePaciente  Quarto: $nomeEnfermaria"
    }

}