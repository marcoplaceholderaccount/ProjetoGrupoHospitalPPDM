package com.example.projetogrupohospital

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
    }
}