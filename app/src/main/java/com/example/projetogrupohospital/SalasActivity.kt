package com.example.projetogrupohospital

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityRegistoDoadorBinding
import com.example.projetogrupohospital.databinding.ActivitySalasBinding

class SalasActivity : AppCompatActivity() {
    private lateinit var janela: ActivitySalasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivitySalasBinding.inflate(layoutInflater)
        setContentView(janela.root)
        val codigosala = intent.getStringExtra("codigo_sala")
        val nomeSala = intent.getStringExtra("nome_sala")
        val tipoSala = intent.getStringExtra("tipo_sala")

        janela.textonomesala.text = nomeSala
        janela.textotiposala.text = tipoSala

        //ola mermao
    }
}