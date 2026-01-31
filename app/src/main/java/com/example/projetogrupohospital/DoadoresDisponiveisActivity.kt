package com.example.projetogrupohospital

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityBancoDeSangueBinding
import com.example.projetogrupohospital.databinding.ActivityDoadoresDisponiveisBinding

class DoadoresDisponiveisActivity : AppCompatActivity() {
    private lateinit var janela: ActivityDoadoresDisponiveisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityDoadoresDisponiveisBinding.inflate(layoutInflater)
        setContentView(janela.root)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            ListaGlobal.listadoadores.map { it.cod + " - " + it.nome + " - " + it.sexo + " - " + it.tiposangue })

        janela.listviewdoadoresdispo.adapter = adapter

    }
}