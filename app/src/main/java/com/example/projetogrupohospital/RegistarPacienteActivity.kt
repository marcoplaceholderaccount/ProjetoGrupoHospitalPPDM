package com.example.projetogrupohospital

import android.os.Bundle
import android.widget.Toast
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

        janela.btnCancelar.setOnClickListener{
            finish()
        }

        janela.btnRegistar.setOnClickListener {

        val nome = janela.pacNome.text.toString().trim()
        val id = janela.pacID.text.toString().trim()
        val data = janela.pacData.text.toString().trim()
        val sexo = janela.pacSexo.text.toString().trim()
        val endereco = janela.pacEndereco.text.toString().trim()

        if (nome.isEmpty() && id.isEmpty() && data.isEmpty() && sexo.isEmpty() && endereco.isEmpty()) {
            Toast.makeText(this,"Preencha todos os campos corretamente",Toast.LENGTH_SHORT).show()
        }

           val paciente = Paciente(nome,id,data,sexo,endereco)
            ListaGlobal.listapacientes.add(paciente)

    }
  }
}