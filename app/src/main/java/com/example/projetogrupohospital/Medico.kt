package com.example.projetogrupohospital

class Medico(
    codigo : String,
    nome : String,
    contacto : String,
    turno : String
) : Profissional(codigo = codigo, nome = nome, contacto = contacto, turno = turno) {
}