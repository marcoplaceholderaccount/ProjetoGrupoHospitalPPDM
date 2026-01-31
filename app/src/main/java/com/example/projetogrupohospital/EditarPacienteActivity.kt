package com.example.projetogrupohospital

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityEditarPacienteBinding
import com.example.projetogrupohospital.databinding.ActivityListarPacienteBinding

class EditarPacienteActivity : AppCompatActivity() {

    private lateinit var janela: ActivityEditarPacienteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        janela= ActivityEditarPacienteBinding.inflate(layoutInflater)
        setContentView(janela.root)

        val codigoRecebido= intent.getStringExtra("CODIGO_PACIENTE")

        val paciente = ListaGlobal.listapacientes.find { it.id == codigoRecebido }

        paciente?.let { p ->
            // Preenche os campos do seu XML
            janela.pacNome.setText(p.nome)
            janela.pacData.setText(p.datanasc)
            janela.pacSexo.setText(p.sexo)
            janela.pacContato.setText(p.contato)
            janela.pacEndereco.setText(p.endereco)



            // Ação do botão Confirmar para salvar as alterações
            janela.btnEditarP.setOnClickListener {
                p.nome = janela.pacNome.text.toString()
                p.datanasc = janela.pacData.text.toString()
                p.sexo = janela.pacSexo.text.toString()
                p.contato = janela.pacContato.text.toString()
                p.endereco = janela.pacEndereco.text.toString()

                Toast.makeText(this, "Editado com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
                    janela.btnCancelar.setOnClickListener{
                        finish()
                    }

        }
    }