package com.example.projetogrupohospital

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityEstatisticasBinding

class EstatisticasActivity : AppCompatActivity() {
    private lateinit var janela: ActivityEstatisticasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityEstatisticasBinding.inflate(layoutInflater)
        setContentView(janela.root)

    }
}