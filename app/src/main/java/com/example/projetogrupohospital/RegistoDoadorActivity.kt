package com.example.projetogrupohospital

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityGestaoSalaBinding
import com.example.projetogrupohospital.databinding.ActivityRegistoDoadorBinding

class RegistoDoadorActivity : AppCompatActivity() {
    private lateinit var janela: ActivityRegistoDoadorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registo_doador)
        janela = ActivityRegistoDoadorBinding.inflate(layoutInflater)
        setContentView(janela.root)

    }
}