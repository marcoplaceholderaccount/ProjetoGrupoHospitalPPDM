package com.example.projetogrupohospital

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityBancoDeSangueBinding
import com.example.projetogrupohospital.databinding.ActivityGestaoSalaBinding
import kotlin.text.get
import kotlin.toString

class GestaoSalaActivity : AppCompatActivity() {
    private lateinit var janela: ActivityGestaoSalaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityGestaoSalaBinding.inflate(layoutInflater)
        setContentView(janela.root)


        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            ListaGlobal.listasalas.map { it.codigo + " - " + it.nome + " - " + it.tipo + " - " + it.quantidade})
        janela.listviewsalas.adapter = adapter
        //PARA QUE A LISTA CONTINUA VISIVEL MESMO TROCANDO DE ACTIVITIES EM VEZ DE SO QND REGISTA
        fun onResume() {
            super.onResume()
            adapter.notifyDataSetChanged()
        }


        //REGISTO DA SALA
        janela.botaoregistarsala.setOnClickListener {

            val codigosala = janela.campocodigosala.text.toString()
            var nomesala = janela.camponomesala.text.toString()
            var tiposala = ""
            var capacidadesala = janela.campocapacidadesala.text.toString()

            var campos = listOf(codigosala,nomesala,capacidadesala)

            if(janela.rbenfermaria.isChecked){
                tiposala = janela.rbenfermaria.text.toString()
            }else if(janela.rbconsultorio.isChecked){
                tiposala = janela.rbconsultorio.text.toString()
            }

            if(campos.any{it.isBlank()}){
                Toast.makeText(this@GestaoSalaActivity, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(ListaGlobal.listasalas.any{it.codigo == codigosala}){
                Toast.makeText(this@GestaoSalaActivity, "Já existe uma sala com esse Código", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }

            lateinit var sala : Sala

            when {
                janela.rbconsultorio.isChecked ->{
                    sala = Consultorio(codigosala,nomesala,tiposala,capacidadesala.toInt())
                }

                janela.rbenfermaria.isChecked ->{
                    sala = Enfermaria(codigosala,nomesala,tiposala,capacidadesala.toInt())
                }
                else->{
                    Toast.makeText(this@GestaoSalaActivity, "Selecione o tipo de sala a ser registada", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener

                }
            }
            Toast.makeText(this@GestaoSalaActivity, "Sala registada com sucesso", Toast.LENGTH_SHORT).show()
            ListaGlobal.listasalas.add(sala)

            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                ListaGlobal.listasalas.map { it.codigo + " - " + it.nome + " - " + it.tipo + " - " + it.quantidade})
            janela.listviewsalas.adapter = adapter
            adapter.notifyDataSetChanged()

        }

        janela.listviewsalas.setOnItemClickListener { _, _, position, _ ->
            val salaselecionada = ListaGlobal.listasalas[position]
            val intent = Intent(this, SalasActivity::class.java)
            intent.putExtra("nome_sala", salaselecionada.nome)
            intent.putExtra("tipo_sala", salaselecionada.tipo)
            intent.putExtra("codigo_sala", salaselecionada.codigo)
            startActivity(intent)

        }

    }
}