package com.example.projetogrupohospital

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityDoacaoBinding
import com.example.projetogrupohospital.databinding.ActivityDoadoresDisponiveisBinding

class DoacaoActivity : AppCompatActivity() {
    private lateinit var janela: ActivityDoacaoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityDoacaoBinding.inflate(layoutInflater)
        setContentView(janela.root)
    }
}