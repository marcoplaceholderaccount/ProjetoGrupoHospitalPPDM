package com.example.projetogrupohospital

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityGestaoPacienteEditarBinding
import com.example.projetogrupohospital.databinding.ActivityTelaGestaoPacienteBinding

class GestaoPacienteEditarActivity : AppCompatActivity() {

    private lateinit var janela: ActivityGestaoPacienteEditarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        janela= ActivityGestaoPacienteEditarBinding.inflate(layoutInflater)
        setContentView(janela.root)

    }
}