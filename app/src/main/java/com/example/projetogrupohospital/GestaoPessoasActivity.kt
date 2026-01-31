package com.example.projetogrupohospital

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.projetogrupohospital.databinding.ActivityGestaoPessoasBinding

class GestaoPessoasActivity : AppCompatActivity() {

    private lateinit var janela: ActivityGestaoPessoasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityGestaoPessoasBinding.inflate(layoutInflater)
        setContentView(janela.root)

        janela.btnFuncionario.setOnClickListener{
            val intent = Intent(this, MenuProfissionalActivity::class.java)
            startActivity(intent)
        }
        janela.btnPaciente.setOnClickListener {
            val intent = Intent(this, TelaGestaoPacienteActivity::class.java)
            startActivity(intent)
        }
    }
}