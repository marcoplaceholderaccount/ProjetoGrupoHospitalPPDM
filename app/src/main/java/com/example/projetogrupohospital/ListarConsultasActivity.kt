package com.example.projetogrupohospital

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListarConsultasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listar_consultas)


        val meuAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, // Layout padrão com um único TextView
            ListaGlobal.listaconsultas
        )


        val listaconsulta = findViewById<ListView>(R.id.lista)
        listaconsulta.adapter = meuAdapter

        ListaGlobal.listaconsultas

    }
}