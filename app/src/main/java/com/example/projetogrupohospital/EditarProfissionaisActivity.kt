package com.example.projetogrupohospital

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityEditarProfissionaisBinding

class EditarProfissionaisActivity : AppCompatActivity() {
    private lateinit var janela : ActivityEditarProfissionaisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityEditarProfissionaisBinding.inflate(layoutInflater)
        setContentView(janela.root)

        // Dentro do onCreate da EditarProfissionaisActivity.kt
        val codigoRecebido = intent.getStringExtra("CODIGO_EXTRA")

        // Busca o profissional na lista pelo código
        val profissional = ListaGlobal.listaprofissional.find { it.codigo == codigoRecebido }

        profissional?.let { p ->
            // Preenche os campos do seu XML
            janela.profNome.text = p.nome
            janela.profNovoNome.setText(p.nome)
            janela.profCont.setText(p.contacto)

            // Configura os Spinners (Turno e Cargo)
            val adapterT = ArrayAdapter(
                this,
                R.layout.simple_spinner_item,
                listOf("Diurno", "Noturno"))
            janela.profTurno.adapter = adapterT
            janela.profTurno.setSelection(if (p.turno == "Diurno") 0 else 1)

            // Ação do botão Confirmar para salvar as alterações
            janela.btnConfirmar.setOnClickListener {
                p.nome = janela.profNome.text.toString()
                p.contacto = janela.profCont.text.toString()
                p.turno = janela.profTurno.selectedItem.toString()

                Toast.makeText(this, "Editado com sucesso!", Toast.LENGTH_SHORT).show()
                finish() // Volta para a lista
            }
        }

        janela.btnCancelar.setOnClickListener { finish() }





    }
}