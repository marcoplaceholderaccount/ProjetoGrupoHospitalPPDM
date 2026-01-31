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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela= ActivityListarPacienteBinding.inflate(layoutInflater)
        setContentView(janela.root)

        val meuAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, // Layout padrão com um único TextView
            ListaGlobal.listapacientes
        )


        val minhaListView = findViewById<ListView>(R.id.listView)
        minhaListView.adapter = meuAdapter

        ListaGlobal.listapacientes



        // 1. Clique Curto: Editar usando o Código do Paciente
        minhaListView.setOnItemClickListener { parent, view, position, id ->
            // Pegamos o objeto Paciente da nossa lista original usando a posição
            val pacienteSelecionado = ListaGlobal.listapacientes[position]
            val codigoPaciente = pacienteSelecionado.id

            val intent = Intent(this, EditarPacienteActivity::class.java)
            intent.putExtra("CODIGO_PACIENTE", codigoPaciente)
            startActivity(intent)
        }

// 2. Clique Longo: Eliminar usando o Código do Paciente
        minhaListView.setOnItemLongClickListener { parent, view, position, id ->
            val pacienteParaRemover = ListaGlobal.listapacientes[position]
            val codigoParaDeletar = pacienteParaRemover.id



            ListaGlobal.listapacientes.removeAt(position)
            meuAdapter.notifyDataSetChanged()

            Toast.makeText(this, "Paciente ${pacienteParaRemover.nome} removido!", Toast.LENGTH_SHORT).show()

            true
        }



    }
}