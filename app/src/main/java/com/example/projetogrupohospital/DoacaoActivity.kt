package com.example.projetogrupohospital

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityDoacaoBinding
import com.example.projetogrupohospital.databinding.ActivityDoadoresDisponiveisBinding
import kotlin.text.get
import kotlin.toString

class DoacaoActivity : AppCompatActivity() {
    private lateinit var janela: ActivityDoacaoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityDoacaoBinding.inflate(layoutInflater)
        setContentView(janela.root)


        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            ListaGlobal.listadoacoes.map { it.codigodoacao + " - " + it.nomedoador + " - " + it.mes+ "-" + it.tipo + " - " + it.quantidade})
        janela.listviewhistoricodoacoes.adapter = adapter
        adapter.notifyDataSetChanged()

        //PREENCHER A LISTA DE DODADORES
        val adapterd = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            ListaGlobal.listadoadores.map { it.cod + " - " + it.nome + "-" + it.tiposangue} )
        janela.listviewdodadores.adapter = adapterd

        //PARA A COMBOBOX TER OS MESES
        val meses = listOf(
            "Janeiro", "Fevereiro", "Março", "Abril",
            "Maio", "Junho", "Julho", "Agosto",
            "Setembro", "Outubro", "Novembro", "Dezembro"
        )
        val adapterMeses = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            meses
        )

        adapterMeses.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        janela.comboboxmes.adapter = adapterMeses


         var doadorselecionado : Doador? = null
        janela.listviewdodadores.setOnItemClickListener { _, _, position, _ ->
            doadorselecionado = ListaGlobal.listadoadores[position]
        }

        //EFETUAR DOACAO
        janela.botaoefetuardoacao.setOnClickListener {
            val codigdonate = janela.campocoddoacao.text.toString()
            var quantidadeTexto = janela.campoquantidade.text.toString()
            var mesdonate = janela.comboboxmes.selectedItem.toString()
            val campos = listOf(codigdonate,quantidadeTexto)
            if(campos.any{it.toString().isEmpty()}){
                Toast.makeText(this@DoacaoActivity, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val quantidadedonate = quantidadeTexto.toDouble()

            for(i in ListaGlobal.listadoacoes){
                if(i.codigodoacao == codigdonate){
                    Toast.makeText(this@DoacaoActivity, "Já existe uma doação com esse codigo!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener

                }
            }
            if(doadorselecionado != null) {
                var novadoacao = Doacao(
                    codigdonate,
                    doadorselecionado.nome,
                    mesdonate, quantidadedonate,
                    doadorselecionado.tiposangue
                )

                ListaGlobal.listadoacoes.add(novadoacao)
                // Salva no Firebase
                FirebaseManager.doacoesRef()
                    .document(novadoacao.codigodoacao)
                    .set(novadoacao)
                Toast.makeText(
                    this@DoacaoActivity,
                    "Doação realizada com sucesso !",
                    Toast.LENGTH_SHORT
                ).show()

                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    ListaGlobal.listadoacoes.map { it.codigodoacao + " - " + it.nomedoador + " - " + it.mes + "-" + it.tipo + " - " + it.quantidade })
                janela.listviewhistoricodoacoes.adapter = adapter
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(
                    this@DoacaoActivity,
                    "Selecione o doador necessario para a doação !",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener

            }

        }


    }
}