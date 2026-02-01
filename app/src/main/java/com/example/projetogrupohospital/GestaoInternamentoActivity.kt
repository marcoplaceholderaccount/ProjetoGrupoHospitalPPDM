package com.example.projetogrupohospital

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projetogrupohospital.databinding.ActivityGestaoInternamentoBinding
import com.google.firebase.firestore.FieldValue

class GestaoInternamentoActivity : AppCompatActivity() {

    private lateinit var janela: ActivityGestaoInternamentoBinding
    private lateinit var adapterInternamento: InternamentoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityGestaoInternamentoBinding.inflate(layoutInflater)
        setContentView(janela.root)

        // Spinners
        janela.spinPac.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ListaGlobal.listapacientes)
        janela.spinQuar.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaEnfermarias())

        // RecyclerView
        adapterInternamento = InternamentoAdapter(mutableListOf()) { intern ->
            processarAlta(intern)
        }
        janela.recyclerInternamentos.layoutManager = LinearLayoutManager(this)
        janela.recyclerInternamentos.adapter = adapterInternamento

        // Quando trocar de enfermaria
        janela.spinQuar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                atualizarInterface()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        janela.btnConfirmar.setOnClickListener { cadastrar() }

        // Preenche inicialmente
        atualizarInterface()
    }

    private fun cadastrar() {
        val cod = janela.codInt.text.toString().trim()
        val dE = janela.dataEnt.text.toString().trim()
        val dS = janela.dataSaid.text.toString().trim()

        val pac = janela.spinPac.selectedItem as? Paciente
        val enf = janela.spinQuar.selectedItem as? Enfermaria

        if (pac == null || enf == null) {
            Toast.makeText(this, "Escolha paciente e enfermaria.", Toast.LENGTH_SHORT).show()
            return
        }

        if (dE.isEmpty() || dS.isEmpty()) {
            Toast.makeText(this, "Preencha datas de entrada e saída.", Toast.LENGTH_SHORT).show()
            return
        }

        if (!enf.temVaga()) {
            Toast.makeText(this, "A sala está lotada!", Toast.LENGTH_SHORT).show()
            return
        }

        val intern = Internamento(
            codigo = cod, // pode ser vazio; se vazio vamos usar add()
            pacienteId = pac.id,
            enfermariaCodigo = enf.codigo.trim(),
            dataEnt = dE,
            dataSaid = dS,
            estado = true
        )

        // Grava no Firestore
        val ref = FirebaseManager.internamentosRef()
        if (intern.codigo.isNotEmpty()) {
            // usar como documento
            ref.document(intern.codigo).set(intern)
                .addOnSuccessListener {
                    // atualizar lista local
                    if (!ListaGlobal.listainternamentostotal.any { it.codigo == intern.codigo }) {
                        ListaGlobal.listainternamentostotal.add(intern)
                    }
                    atualizarInterface()
                    limparCampos()
                    Toast.makeText(this, "Internamento registado.", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Erro ao registar internamento: ${e.message}", Toast.LENGTH_LONG).show()
                }
        } else {
            // add() gera id -> actualiza o campo codigo no documento
            ref.add(intern)
                .addOnSuccessListener { docRef ->
                    intern.codigo = docRef.id
                    // actualiza o próprio documento com o campo codigo preenchido
                    ref.document(docRef.id).set(intern)
                        .addOnSuccessListener {
                            ListaGlobal.listainternamentostotal.add(intern)
                            atualizarInterface()
                            limparCampos()
                            Toast.makeText(this, "Internamento registado.", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this, "Erro a finalizar registo: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Erro ao registar internamento: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }

        // Opcional: se quiseres manter um campo na sala com array de ids, descomenta:
        /*
        val salaRef = FirebaseManager.salasRef().document(enf.codigo)
        salaRef.update("listainternamentos", FieldValue.arrayUnion(intern.codigo))
            .addOnSuccessListener { Log.d("GestaoIntern", "Sala actualizada") }
            .addOnFailureListener { Log.e("GestaoIntern", "Erro a actualizar sala", it) }
        */
    }

    private fun processarAlta(intern: Internamento) {
        // actualiza localmente (mesma instância na lista global)
        intern.estado = false

        // actualiza Firestore
        val ref = FirebaseManager.internamentosRef()
        if (intern.codigo.isNotEmpty()) {
            ref.document(intern.codigo).update("estado", false)
                .addOnSuccessListener {
                    atualizarInterface()
                    Toast.makeText(this, "Alta processada.", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    // reverte localmente se falhar
                    intern.estado = true
                    Toast.makeText(this, "Erro ao processar alta: ${e.message}", Toast.LENGTH_LONG).show()
                }
        } else {
            // procura por documento com campo codigo igual (fallback)
            ref.whereEqualTo("codigo", intern.codigo).get()
                .addOnSuccessListener { snapshot ->
                    if (!snapshot.isEmpty) {
                        val docId = snapshot.documents[0].id
                        ref.document(docId).update("estado", false)
                            .addOnSuccessListener {
                                atualizarInterface()
                                Toast.makeText(this, "Alta processada.", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener { e ->
                                intern.estado = true
                                Toast.makeText(this, "Erro ao processar alta: ${e.message}", Toast.LENGTH_LONG).show()
                            }
                    } else {
                        intern.estado = true
                        Toast.makeText(this, "Documento do internamento não encontrado.", Toast.LENGTH_LONG).show()
                    }
                }
                .addOnFailureListener { e ->
                    intern.estado = true
                    Toast.makeText(this, "Erro ao procurar internamento: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }
    }

    private fun atualizarInterface() {
        val enf = janela.spinQuar.selectedItem as? Enfermaria ?: return
        val ativos = enf.getInternamentosAtivos()
        adapterInternamento.atualizar(ativos)
        // podes actualizar contadores UI aqui, por exemplo:
        //janela.txtOcupacao.text = "${enf.ocupacaoActual()} / ${enf.quantidade}"
    }

    private fun limparCampos() {
        janela.codInt.text.clear()
        janela.dataEnt.text.clear()
        janela.dataSaid.text.clear()
    }

    fun listaEnfermarias(): List<Enfermaria> = ListaGlobal.listasalas.filterIsInstance<Enfermaria>()
}
