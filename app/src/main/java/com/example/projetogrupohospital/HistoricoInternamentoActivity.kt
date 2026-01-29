package com.example.projetogrupohospital

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityHistoricoInternamentoBinding

class HistoricoInternamentoActivity : AppCompatActivity() {
    private lateinit var janela : ActivityHistoricoInternamentoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityHistoricoInternamentoBinding.inflate(layoutInflater)
        setContentView(janela.root)
    }
}