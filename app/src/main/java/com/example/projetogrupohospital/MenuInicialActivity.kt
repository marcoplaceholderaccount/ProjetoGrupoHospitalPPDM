package com.example.projetogrupohospital

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityMainBinding
import com.example.projetogrupohospital.databinding.ActivityMenuInicialBinding

class MenuInicialActivity : AppCompatActivity() {
    private lateinit var janela : ActivityMenuInicialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityMenuInicialBinding.inflate(layoutInflater)
        setContentView(janela.root)

        janela.botaogestaosala.setOnClickListener {
            val intent = Intent(this, GestaoSalaActivity::class.java)
            startActivity(intent)
        }

        janela.botaobancodesangue.setOnClickListener {
            val intent = Intent(this, BancoDeSangueActivity::class.java)
            startActivity(intent)
        }

        janela.botaoestatistica.setOnClickListener {
            val intent = Intent(this, EstatisticasActivity::class.java)
            startActivity(intent)
        }

    }
}