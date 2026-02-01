package com.example.projetogrupohospital

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.projetogrupohospital.databinding.ActivityConsultaBinding

class ConsultaActivity : AppCompatActivity() {

    private lateinit var janela: ActivityConsultaBinding

    private var pacienteSelecionado: Paciente? = null
    private var medicoSelecionado: Profissional? = null
    private var consultorioSelecionado: Sala? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityConsultaBinding.inflate(layoutInflater)
        setContentView(janela.root)

        // --- LISTAS (filtragem por campo "tipo") ---
        val pacientes = ListaGlobal.listapacientes
        val medicos = listaMedicos()              // ✅ corrigido
        val consultorios = listaConsultorio()     // ✅ corrigido

        // --- ADAPTERS ---
        janela.listviewpacientes.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            pacientes.map { "${it.id} - ${it.nome}" }
        )

        janela.listviewmedicos.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            medicos.map { "${it.codigo} - ${it.nome}" }
        )

        janela.listviewconsultorios.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            consultorios.map { "${it.codigo} - ${it.nome}" }
        )

        // --- SELECÇÃO PACIENTE ---
        janela.listviewpacientes.setOnItemClickListener { _, _, position, _ ->
            pacienteSelecionado = pacientes[position]
            janela.txtcodpaciente.text = pacienteSelecionado!!.id
            janela.txtnomepaciente.text = pacienteSelecionado!!.nome
        }

        // --- SELECÇÃO MÉDICO ---
        janela.listviewmedicos.setOnItemClickListener { _, _, position, _ ->
            medicoSelecionado = medicos[position]
            janela.txtcodmedico.text = medicoSelecionado!!.codigo
            janela.txtnomemadico.text = medicoSelecionado!!.nome
        }

        // --- SELECÇÃO CONSULTÓRIO ---
        janela.listviewconsultorios.setOnItemClickListener { _, _, position, _ ->
            consultorioSelecionado = consultorios[position]
            janela.txtcodconsultorio.text = consultorioSelecionado!!.codigo
            janela.txtnomeconsultorio.text = consultorioSelecionado!!.nome
        }

        // --- GUARDAR CONSULTA ---
        janela.botaorealizarconsulta.setOnClickListener {

            val codigoConsulta = janela.campocodigoconsulta.text.toString().trim()
            val dataConsulta = janela.campodataconsulta.text.toString().trim()

            if (codigoConsulta.isEmpty() || dataConsulta.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pacienteSelecionado == null || medicoSelecionado == null || consultorioSelecionado == null) {
                Toast.makeText(this, "Selecione paciente, médico e consultório.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (ListaGlobal.listaconsultas.any { it.codigoconsulta == codigoConsulta }) {
                Toast.makeText(this, "Já existe uma consulta com esse código!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val novaConsulta = Consulta(
                codigoconsulta = codigoConsulta,
                pacienteId = pacienteSelecionado!!.id,
                medicoCodigo = medicoSelecionado!!.codigo,
                consultorioCodigo = consultorioSelecionado!!.codigo,
                data = dataConsulta
            )

            FirebaseManager.consultasRef()
                .document(novaConsulta.codigoconsulta)
                .set(novaConsulta)
                .addOnSuccessListener {
                    ListaGlobal.listaconsultas.add(novaConsulta)
                    Toast.makeText(this, "Consulta marcada com sucesso", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Erro a guardar consulta: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }
    }

    // --- Filtra médicos por "tipo" dentro dos profissionais ---
    fun listaMedicos(): List<Profissional> {
        return ListaGlobal.listaprofissional.filter {
            it.tipo.equals("Medico", true) || it.tipo.equals("Médico", true)
        }
    }

    // --- Filtra consultórios por "tipo" dentro das salas ---
    fun listaConsultorio(): List<Sala> {
        return ListaGlobal.listasalas.filter {
            it.tipo.equals("Consultorio", true) || it.tipo.equals("Consultório", true)
        }
    }
}
