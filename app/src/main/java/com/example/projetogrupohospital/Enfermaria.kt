package com.example.projetogrupohospital

class Enfermaria(
    codigo : String = "",
    nome : String = "",
    tipo : String = "",
    quantidade : Int = 0
) : Sala(codigo, nome, tipo, quantidade) {

    // Obtém internamentos desta enfermaria a partir da lista global
    fun getInternamentos(): List<Internamento> {
        val key = this.codigo.trim().uppercase()
        return ListaGlobal.listainternamentostotal.filter {
            it.enfermariaCodigo.trim().uppercase() == key
        }
    }

    // Apenas os activos (estado == true)
    fun getInternamentosAtivos(): List<Internamento> = getInternamentos().filter { it.estado }

    // Ocupação actual (nº de internamentos activos)
    fun ocupacaoActual(): Int = getInternamentosAtivos().size

    fun temVaga(): Boolean = ocupacaoActual() < quantidade

}
