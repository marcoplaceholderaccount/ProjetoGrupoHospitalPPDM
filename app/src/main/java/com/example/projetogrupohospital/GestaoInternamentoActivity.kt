package com.example.projetogrupohospital

import InternamentoAdapter
import android.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityGestaoInternamentoBinding
import kotlin.collections.mutableListOf

class GestaoInternamentoActivity : AppCompatActivity() {
    private lateinit var janela : ActivityGestaoInternamentoBinding
    private lateinit var adapterInternamento: InternamentoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityGestaoInternamentoBinding.inflate(layoutInflater)
        setContentView(janela.root)

        // Configuração dos Spinners
        janela.spinPac.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ListaGlobal.listapacientes)
        janela.spinQuar.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaEnfermarias())

        // Configuração do clique no RecyclerView para dar alta
        adapterInternamento = InternamentoAdapter(mutableListOf()) { internamentoClicado ->

            val enf = internamentoClicado.enfermaria
            enf.darAlta(internamentoClicado)

            atualizarInterface()
            Toast.makeText(this, "Alta processada na ${enf.nome}", Toast.LENGTH_SHORT).show()
        }
        janela.recyclerInternamentos.adapter = adapterInternamento

        // Atualiza a lista quando trocar de enfermaria no Spinner
        janela.spinQuar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                atualizarInterface()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        janela.btnConfirmar.setOnClickListener { cadastrar() }
    }

    private fun cadastrar() {
        val cod = janela.codInt.text.toString()
        val dE = janela.dataEnt.text.toString()
        val dS = janela.dataSaid.text.toString()
        val pac = janela.spinPac.selectedItem as Paciente
        val enf = janela.spinQuar.selectedItem as Enfermaria

        if (cod.isNotEmpty() && dE.isNotEmpty() && dS.isNotEmpty()) {
            // Criação do internamento passando a enfermaria atual
            val novo = Internamento(cod, pac, enf, dE, dS, true)
            if (enf.listainternamentos.size < enf.quantidade) {
                enf.listainternamentos.add(novo)
                ListaGlobal.listainternamentostotal.add(novo)
                atualizarInterface()
                limparCampos()
            } else {
                Toast.makeText(this,"A sala está lotada!",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun atualizarInterface() {
        val enf = janela.spinQuar.selectedItem as Enfermaria
        // filtragem apenas de quem ainda está ativo (estado == true)
        val ativos = enf.listainternamentos.filter { it.estado }
        adapterInternamento.atualizar(ativos)
    }

    private fun limparCampos() {
        janela.codInt.text.clear()
        janela.dataEnt.text.clear()
        janela.dataSaid.text.clear()
    }

    fun listaEnfermarias(): List<Enfermaria> = ListaGlobal.listasalas.filterIsInstance<Enfermaria>()
}
