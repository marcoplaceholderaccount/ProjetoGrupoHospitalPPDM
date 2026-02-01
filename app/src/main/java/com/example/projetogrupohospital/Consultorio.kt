package com.example.projetogrupohospital

class Consultorio(
    codigo: String = "",
    nome: String = "",
    quantidade: Int = 0
) : Sala(
    codigo = codigo,
    nome = nome,
    tipo = "Consultorio",       // ✅ NOVO
    quantidade = quantidade
)
