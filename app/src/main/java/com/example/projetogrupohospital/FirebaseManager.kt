package com.example.projetogrupohospital

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

object FirebaseManager {

    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun doacoesRef() = db.collection("doacoes")
    fun doadoresRef() = db.collection("doadores")
    fun pacientesRef() = db.collection("pacientes")
    fun profissionaisRef() = db.collection("profissionais")
    fun salasRef() = db.collection("salas")
    fun consultasRef() = db.collection("consultas")

    fun internamentosRef() = db.collection("internamentos")

}
