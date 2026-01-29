package com.example.projetogrupohospital

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityGestaoPessoasBinding
import com.example.projetogrupohospital.databinding.ActivityTelaGestaoPacienteBinding

class GestaoPessoasActivity : AppCompatActivity() {

    private lateinit var janela: ActivityGestaoPessoasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        janela= ActivityGestaoPessoasBinding.inflate(layoutInflater)
        setContentView(janela.root)


    }
}