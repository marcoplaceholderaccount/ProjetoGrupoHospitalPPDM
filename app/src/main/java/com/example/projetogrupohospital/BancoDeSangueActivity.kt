package com.example.projetogrupohospital

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityBancoDeSangueBinding
import com.example.projetogrupohospital.databinding.ActivityEstatisticasBinding

class BancoDeSangueActivity : AppCompatActivity() {
    private lateinit var janela: ActivityBancoDeSangueBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityBancoDeSangueBinding.inflate(layoutInflater)
        setContentView(janela.root)

        janela.botaodoadoresdipo.setOnClickListener {
            val intent = Intent(this, DoadoresDisponiveisActivity::class.java)
            startActivity(intent)
        }

        janela.botaoregistardoadores.setOnClickListener {
            val intent = Intent(this, RegistoDoadorActivity::class.java)
            startActivity(intent)
        }

        janela.botaodoacao.setOnClickListener {
            val intent = Intent(this, DoacaoActivity::class.java)
            startActivity(intent)
        }

    }
}