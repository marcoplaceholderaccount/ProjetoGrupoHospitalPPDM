package com.example.projetogrupohospital

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityConsultaBinding

class ConsultaActivity : AppCompatActivity() {
    private lateinit var janela: ActivityConsultaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityConsultaBinding.inflate(layoutInflater)
        setContentView(janela.root)

        val adapterp = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            ListaGlobal.listapacientes.map { it.id + " - " + it.nome})
        janela.listviewpacientes.adapter = adapterp

        val adapterm = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaMedicos().map { it.codigo + " - " + it.nome})
        janela.listviewmedicos.adapter = adapterm


        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaConsultorio().map { it.codigo + " - " + it.nome})
        janela.listviewconsultorios.adapter = adapter

        //AO CLICAR NA LISTVIEW  DE PACIENTES
        lateinit var pacienteselecionado : Paciente
        janela.listviewpacientes.setOnItemClickListener { _, _, position, _ ->
            pacienteselecionado = ListaGlobal.listapacientes[position]

            janela.txtcodpaciente.text = pacienteselecionado.id.toString()
            janela.txtnomepaciente.text = pacienteselecionado.nome
        }

        //AO CLICAR NA LISTVIEW DE MEDICOS
        lateinit var medicoselecionado : Medico
        janela.listviewmedicos.setOnItemClickListener { _, _, position, _ ->
            medicoselecionado = listaMedicos()[position]
            janela.txtcodmedico.text = medicoselecionado.codigo
            janela.txtnomemadico.text = medicoselecionado.nome
        }

        //AO CLICAR NA LISTVIEW DE CONSUTORIOS
        lateinit var consultorioselecionado : Consultorio
        janela.listviewconsultorios.setOnItemClickListener { _, _, position, _ ->
            consultorioselecionado = listaConsultorio()[position]
            janela.txtcodconsultorio.text = consultorioselecionado.codigo
            janela.txtnomeconsultorio.text = consultorioselecionado.nome
        }

        janela.botaorealizarconsulta.setOnClickListener {
            var codigoc = janela.campocodigoconsulta.text.toString()
            var datac = janela.campodataconsulta.text.toString()

            val campos = listOf(codigoc,datac)
            if(campos.any(){it.isBlank()}){
                Toast.makeText(this@ConsultaActivity, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selecoes = listOf(pacienteselecionado, medicoselecionado, consultorioselecionado)
            if(selecoes.any(){it == null }){
                Toast.makeText(this@ConsultaActivity, "Selecione todos os membros das listas", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            for(i in ListaGlobal.listaconsultas){
                if(i.codigoconsulta == codigoc ){
                    Toast.makeText(this@ConsultaActivity, "Já existe uma consulta com esse código!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            var novaconsulta = Consulta(codigoc,
                pacienteselecionado.nome,
                medicoselecionado.nome,
                consultorioselecionado.nome,
                datac)
            ListaGlobal.listaconsultas.add(novaconsulta)



        }
    }

    fun listaMedicos() : MutableList<Medico>{
        var lista : MutableList<Medico> = mutableListOf()
        for (i in ListaGlobal.listaprofissional){
            if(i is Medico){
                lista.add(i)
            }
        }
        return lista
    }

    fun listaConsultorio() : MutableList<Consultorio>{
        var lista : MutableList<Consultorio> = mutableListOf()
        for (i in ListaGlobal.listasalas){
            if(i is Consultorio){
                lista.add(i)
            }
        }
        return lista
    }

}