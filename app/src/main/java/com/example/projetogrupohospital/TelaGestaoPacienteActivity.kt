package com.example.projetogrupohospital

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityGestaoPacienteEditarBinding
import com.example.projetogrupohospital.databinding.ActivityTelaGestaoPacienteBinding

class TelaGestaoPacienteActivity : AppCompatActivity() {

    private lateinit var janela: ActivityTelaGestaoPacienteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela= ActivityTelaGestaoPacienteBinding.inflate(layoutInflater)
        setContentView(janela.root)

        val botao1= janela.btnRegistarP
        botao1.setOnClickListener{

            val intent= Intent(this, RegistarPacienteActivity::class.java)
            startActivity(intent)
        }

        val botao2= janela.btnEditarP
        botao2.setOnClickListener {

            val intent= Intent(this, EditarPacienteActivity:: class.java)

        }

        val botao3= janela.btnListarP
        botao3.setOnClickListener{
            val Intent= Intent(this, ListarPacienteActivity:: class.java)
        }
    }
}