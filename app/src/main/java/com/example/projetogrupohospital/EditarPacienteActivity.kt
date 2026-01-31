package com.example.projetogrupohospital

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityEditarPacienteBinding
import com.example.projetogrupohospital.databinding.ActivityTelaGestaoPacienteBinding

class EditarPacienteActivity : AppCompatActivity() {

    private lateinit var janela: ActivityEditarPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        janela= ActivityEditarPacienteBinding.inflate(layoutInflater)
        setContentView(janela.root)




    }
}