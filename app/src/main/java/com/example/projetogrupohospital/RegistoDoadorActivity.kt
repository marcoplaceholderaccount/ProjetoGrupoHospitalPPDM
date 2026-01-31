package com.example.projetogrupohospital

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityGestaoSalaBinding
import com.example.projetogrupohospital.databinding.ActivityRegistoDoadorBinding
import kotlin.toString

class RegistoDoadorActivity : AppCompatActivity() {
    private lateinit var janela: ActivityRegistoDoadorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registo_doador)
        janela = ActivityRegistoDoadorBinding.inflate(layoutInflater)
        setContentView(janela.root)


        //CLASSE CRIADA PARA QUE AS CHECKLISTS POSSUEM CHECKBOXES
        class ChecklistAdapter(
            private val context: Context,
            private val itens: MutableList<Checklistitem>
        ) : BaseAdapter() {

            override fun getCount() = itens.size
            override fun getItem(position: Int) = itens[position]
            override fun getItemId(position: Int) = position.toLong()

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = convertView ?: LayoutInflater.from(context)
                    .inflate(R.layout.item_checklistdoador, parent, false)

                val txtItem = view.findViewById<TextView>(R.id.txtItem)
                val checkConforme = view.findViewById<CheckBox>(R.id.checkConforme)

                val item = itens[position]

                txtItem.text = item.descricao

                checkConforme.setOnCheckedChangeListener(null)
                checkConforme.isChecked = item.conforme

                checkConforme.setOnCheckedChangeListener { _, isChecked ->
                    item.conforme = isChecked
                }

                return view
            }
        }

        //CONTEUDOS DA CHECKLIST DE REQUISITOS PARA DOAR
        val checklistrequisitos = mutableListOf(
            Checklistitem("Peso minimo de 50kg"),
            Checklistitem("Está Saúdavel no dia da Doação"),
            Checklistitem("Não possui DSTs"),
            Checklistitem("Não realizou nenhuma cirurgia ou tatuagem"),
            Checklistitem("Não ingeriu álcool nas últimas 12 horas")
        )
        //PARA QUE A COMBOBOX DO TIPO SANGUINEO POSSUI ITENS
        val tiposSanguineos = listOf("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
        val adapters = ArrayAdapter(this, android.R.layout.simple_spinner_item, tiposSanguineos)
        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        janela.opcaosangue.adapter = adapters

        janela.lisviewrequisitosdoar.adapter = ChecklistAdapter(this, checklistrequisitos)

        //BOTAO PARA REGISTAR DOADOR
        janela.botaoregistard.setOnClickListener {

            val codigod = janela.campocodd.text.toString()
            var nomed = janela.camponomed.text.toString()
            var datanascd = janela.camponascd.text.toString()
            var tsangue = janela.opcaosangue.selectedItem.toString()
            var sexod = ""
            if (janela.radiomasculino.isChecked) {
                sexod = janela.radiomasculino.text.toString()
            } else if (janela.radiofemenino.isChecked) {
                sexod = janela.radiofemenino.text.toString()
            }

            val campos = listOf(codigod,nomed,datanascd,sexod)
            if(campos.any(){it.isBlank()}){
                Toast.makeText(this@RegistoDoadorActivity, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if( checklistrequisitos.all { !it.conforme } ){
                Toast.makeText(this@RegistoDoadorActivity, "Doador reprovado nos requisitos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            for(i in ListaGlobal.listadoadores) {
                if(i.cod == codigod){
                    Toast.makeText(this@RegistoDoadorActivity, "Já existe doador com esse código!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            val doador = Doador(codigod, nomed, sexod, tsangue,datanascd)
            ListaGlobal.listadoadores.add(doador)
            Toast.makeText(this@RegistoDoadorActivity, "Doador registado com sucesso!", Toast.LENGTH_SHORT).show()

        }



    }
}