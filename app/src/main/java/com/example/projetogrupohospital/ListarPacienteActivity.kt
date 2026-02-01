package com.example.projetogrupohospital

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityListarPacienteBinding
import com.example.projetogrupohospital.databinding.ActivityTelaGestaoPacienteBinding
import kotlin.jvm.java

class ListarPacienteActivity : AppCompatActivity() {

    private lateinit var janela: ActivityListarPacienteBinding
    private lateinit var meuAdapter: ArrayAdapter<Paciente>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela= ActivityListarPacienteBinding.inflate(layoutInflater)
        setContentView(janela.root)

        meuAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, // Layout padrão com um único TextView
            ListaGlobal.listapacientes
        )

        val listaPaciente = findViewById<ListView>(R.id.listView)
        listaPaciente.adapter = meuAdapter

        //  Clique Curto para Editar usando o Código do Paciente
        listaPaciente.setOnItemClickListener { parent, view, position, id ->

            val pacienteSelecionado = ListaGlobal.listapacientes[position]
            val codigoPaciente = pacienteSelecionado.id

            val intent = Intent(this, EditarPacienteActivity::class.java)
            intent.putExtra("CODIGO_PACIENTE", codigoPaciente)
            startActivity(intent)

        }

        //  Clique Longo para eliminar usando o Código do Paciente
        listaPaciente.setOnItemLongClickListener { parent, view, position, id ->
            val pacienteParaRemover = ListaGlobal.listapacientes[position]
            val codigoParaDeletar = pacienteParaRemover.id
            ListaGlobal.listapacientes.removeAt(position)

            meuAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Paciente ${pacienteParaRemover.nome} removido!", Toast.LENGTH_SHORT).show()

            true
        }



    }

    override fun onResume() {
        super.onResume()
        meuAdapter.notifyDataSetChanged()
    }
}