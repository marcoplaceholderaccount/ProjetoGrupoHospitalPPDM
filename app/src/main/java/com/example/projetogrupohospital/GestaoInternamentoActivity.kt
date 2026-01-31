package com.example.projetogrupohospital

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityGestaoInternamentoBinding

class GestaoInternamentoActivity : AppCompatActivity() {
    private lateinit var janela : ActivityGestaoInternamentoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityGestaoInternamentoBinding.inflate(layoutInflater)
        setContentView(janela.root)

        val adapterT = ArrayAdapter(
            this,
            R.layout.simple_spinner_item,
            ListaGlobal.listapacientes)
        janela.spinPac.adapter = adapterT

        val adapterP = ArrayAdapter(
            this,
            R.layout.simple_spinner_item,
            listaEnfermarias()
        )
        janela.spinQuar

        janela.btnConfirmar.setOnClickListener {
            val cod = janela.codInt.text.toString()
            val dataE = janela.dataEnt.text.toString()
            val dataS = janela.dataSaid.text.toString()
            val pac = janela.spinPac.selectedItem as Paciente
            val quarto = janela.spinQuar.selectedItem as Enfermaria

            if (cod.isNotEmpty() && dataE.isNotEmpty() && dataE.isNotEmpty() && dataS.isNotEmpty()) {
                if (quarto.listainternamentos.size < quarto.quantidade) {
                    val internamento = Internamento(cod, pac, dataS, dataS, true)
                    quarto.listainternamentos.add(internamento)

                    Toast.makeText(this, "Internamento registado na ${quarto.nome}", Toast.LENGTH_SHORT).show()

                    // Limpar os campos para a próxima entrada
                    janela.codInt.text.clear()
                    janela.dataEnt.text.clear()
                    janela.dataSaid.text.clear()
                } else {
                    Toast.makeText(this, "Enfermaria lotada!", Toast.LENGTH_SHORT).show()
                }
            }else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }



    }

    fun listaEnfermarias() : MutableList<Enfermaria>{
        var lista : MutableList<Enfermaria> = mutableListOf()
        for (i in ListaGlobal.listasalas){
            if(i is Enfermaria){
                lista.add(i)
            }
        }
        return lista
    }



}