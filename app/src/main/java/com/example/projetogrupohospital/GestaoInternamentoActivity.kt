package com.example.projetogrupohospital

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityGestaoInternamentoBinding

class GestaoInternamentoActivity : AppCompatActivity() {
    private lateinit var janela : ActivityGestaoInternamentoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityGestaoInternamentoBinding.inflate(layoutInflater)
        setContentView(janela.root)

    }
}