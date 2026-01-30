package com.example.projetogrupohospital

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityRegistarPacienteBinding
import com.example.projetogrupohospital.databinding.ActivityTelaGestaoPacienteBinding

class RegistarPacienteActivity : AppCompatActivity() {

    private lateinit var janela: ActivityRegistarPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        janela= ActivityRegistarPacienteBinding.inflate(layoutInflater)
        setContentView(janela.root)

    }
}