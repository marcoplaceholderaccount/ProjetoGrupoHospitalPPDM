package com.example.projetogrupohospital

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityListarPacienteBinding
import com.example.projetogrupohospital.databinding.ActivityTelaGestaoPacienteBinding

class ListarPacienteActivity : AppCompatActivity() {

    private lateinit var janela: ActivityListarPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela= ActivityListarPacienteBinding.inflate(layoutInflater)
        setContentView(janela.root)

    }
}