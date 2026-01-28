package com.example.projetogrupohospital

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityGestaoProfissionaisBinding

class GestaoProfissionaisActivity : AppCompatActivity() {
    private lateinit var janela : ActivityGestaoProfissionaisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityGestaoProfissionaisBinding.inflate(layoutInflater)
        setContentView(janela.root)
    }
}