package com.example.projetogrupohospital

import android.content.Intent
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
        janela = ActivityGestaoPessoasBinding.inflate(layoutInflater)
        setContentView(janela.root)

        val botao1= janela.btnFuncionario
        botao1.setOnClickListener {
            val intent = Intent(this, MenuProfissionaisActivity::class.java)
            startActivity(intent)
        }
        val botao2= janela.btnPaciente
        botao2.setOnClickListener {
            val intent = Intent(this, TelaGestaoPacienteActivity::class.java)
            startActivity(intent)
        }
    }
}