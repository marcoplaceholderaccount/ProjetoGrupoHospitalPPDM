package com.example.projetogrupohospital

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.projetogrupohospital.databinding.ActivityGestaoSalaBinding

class GestaoSalaActivity : AppCompatActivity() {

    private lateinit var janela: ActivityGestaoSalaBinding

    // lista de strings para o adapter
    private val linhas = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityGestaoSalaBinding.inflate(layoutInflater)
        setContentView(janela.root)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, linhas)
        janela.listviewsalas.adapter = adapter

        atualizarLista()

        janela.botaoregistarsala.setOnClickListener {

            val codigosala = janela.campocodigosala.text.toString().trim()
            val nomesala = janela.camponomesala.text.toString().trim()
            val capacidadesalaStr = janela.campocapacidadesala.text.toString().trim()

            if (codigosala.isBlank() || nomesala.isBlank() || capacidadesalaStr.isBlank()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val capacidade = try {
                capacidadesalaStr.toInt()
            } catch (e: Exception) {
                Toast.makeText(this, "Capacidade inválida!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (ListaGlobal.listasalas.any { it.codigo == codigosala }) {
                Toast.makeText(this, "Já existe uma sala com esse Código", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sala: Sala = when {
                janela.rbconsultorio.isChecked -> {
                    // ✅ métodos 1: Consultorio define tipo sozinho
                    Consultorio(codigosala, nomesala, capacidade)
                }

                janela.rbenfermaria.isChecked -> {
                    // Enfermaria mantém tipo como vem do rádio
                    Enfermaria(codigosala, nomesala, "Enfermaria", capacidade)
                }

                else -> {
                    Toast.makeText(this, "Selecione o tipo de sala a ser registada", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            // Guardar no Firestore com confirmação
            FirebaseManager.salasRef()
                .document(sala.codigo)
                .set(sala)
                .addOnSuccessListener {
                    ListaGlobal.listasalas.add(sala)
                    Toast.makeText(this, "Sala registada com sucesso", Toast.LENGTH_SHORT).show()
                    limparCampos()
                    atualizarLista()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Erro ao guardar sala: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }
    }

    override fun onResume() {
        super.onResume()
        atualizarLista()
    }

    private fun atualizarLista() {
        linhas.clear()
        linhas.addAll(
            ListaGlobal.listasalas.map { "${it.codigo} - ${it.nome} - ${it.tipo} - ${it.quantidade}" }
        )
        adapter.notifyDataSetChanged()
    }

    private fun limparCampos() {
        janela.campocodigosala.text.clear()
        janela.camponomesala.text.clear()
        janela.campocapacidadesala.text.clear()
        janela.rbconsultorio.isChecked = false
        janela.rbenfermaria.isChecked = false
    }
}
