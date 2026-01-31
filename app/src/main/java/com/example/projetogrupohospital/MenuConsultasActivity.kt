package com.example.projetogrupohospital

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.projetogrupohospital.databinding.ActivityMenuConsultaBinding

class MenuConsultasActivity : AppCompatActivity() {

    private lateinit var janela: ActivityMenuConsultaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1. Inflar a janela corretamente
        janela = ActivityMenuConsultaBinding.inflate(layoutInflater)

        // 2. Usar o root da janela no setContentView
        setContentView(janela.root)

        // 3. Agora o clique vai funcionar sem crashar
        janela.btnConsulta.setOnClickListener {
            val intent = Intent(this, Consulta::class.java)
            startActivity(intent)
        }
    }
}