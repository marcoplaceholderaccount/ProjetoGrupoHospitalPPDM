package com.example.projetogrupohospital

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityMenuConsultasBinding

class MenuConsultasActivity : AppCompatActivity() {
    private lateinit var janela : ActivityMenuConsultasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityMenuConsultasBinding.inflate(layoutInflater)
        setContentView(janela.root)

    }
}