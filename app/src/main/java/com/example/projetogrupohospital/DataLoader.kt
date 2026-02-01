package com.example.projetogrupohospital

import android.util.Log
import com.google.firebase.firestore.QuerySnapshot
import java.util.concurrent.atomic.AtomicInteger

object DataLoader {
    private const val TAG = "DataLoader"

    // Lista de tarefas que vamos executar:
    // doacoes, doadores, pacientes, consultas, profissionais, salas, internamentos
    // Nota: internamentos só são carregados depois das salas (para garantir associação se necessário).
    fun carregarTudo(onFinish: (() -> Unit)? = null) {
        val totalTasks = AtomicInteger(7)
        val completed = AtomicInteger(0)

        fun taskDone() {
            val done = completed.incrementAndGet()
            Log.d(TAG, "DataLoader: tarefa concluída ($done/7)")
            if (done >= totalTasks.get()) {
                Log.i(TAG, "DataLoader: todas as tarefas concluídas")
                onFinish?.invoke()
            }
        }

        // Carrega colecções que não têm dependência
        carregarDoacoes { taskDone() }
        carregarDoadores { taskDone() }
        carregarPacientes { taskDone() } // pacientes antes de salas não é obrigatório, mas carregamos
        carregarConsultas { taskDone() }
        carregarProfissionais { taskDone() }

        // Carregar salas e só depois internamentos
        carregarSalas {
            taskDone() // salas concluídas
            carregarInternamentos {
                taskDone() // internamentos concluídos
            }
        }
    }

    private fun carregarDoacoes(done: () -> Unit) {
        ListaGlobal.listadoacoes.clear()
        FirebaseManager.doacoesRef().get()
            .addOnSuccessListener { snapshot ->
                snapshot.documents.forEach { doc ->
                    try {
                        val d = doc.toObject(Doacao::class.java)
                        d?.let { ListaGlobal.listadoacoes.add(it) }
                    } catch (e: Exception) {
                        Log.e(TAG, "Erro a desserializar Doacao ${doc.id}", e)
                    }
                }
                done()
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Erro a carregar doacoes", e)
                done()
            }
    }

    private fun carregarDoadores(done: () -> Unit) {
        ListaGlobal.listadoadores.clear()
        FirebaseManager.doadoresRef().get()
            .addOnSuccessListener { snapshot ->
                snapshot.documents.forEach { doc ->
                    try {
                        val d = doc.toObject(Doador::class.java)
                        d?.let { ListaGlobal.listadoadores.add(it) }
                    } catch (e: Exception) {
                        Log.e(TAG, "Erro a desserializar Doador ${doc.id}", e)
                    }
                }
                done()
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Erro a carregar doadores", e)
                done()
            }
    }

    private fun carregarPacientes(done: () -> Unit) {
        ListaGlobal.listapacientes.clear()
        FirebaseManager.pacientesRef().get()
            .addOnSuccessListener { snapshot ->
                snapshot.documents.forEach { doc ->
                    try {
                        val nome = doc.getString("nome") ?: ""
                        val id = doc.getString("id") ?: doc.id
                        val datanasc = doc.getString("datanasc") ?: ""
                        val contato = doc.getString("contato") ?: ""
                        val sexo = doc.getString("sexo") ?: ""
                        val endereco = doc.getString("endereco") ?: ""

                        val p = Paciente(nome = nome, id = id, datanasc = datanasc, contato = contato, sexo = sexo, endereco = endereco)
                        ListaGlobal.listapacientes.add(p)
                    } catch (e: Exception) {
                        Log.e(TAG, "Erro a parsear paciente ${doc.id}", e)
                    }
                }
                done()
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Erro a carregar pacientes", e)
                done()
            }
    }

    private fun carregarConsultas(done: () -> Unit) {
        ListaGlobal.listaconsultas.clear()
        FirebaseManager.consultasRef().get()
            .addOnSuccessListener { snapshot ->
                for (doc in snapshot.documents) {
                    val c = Consulta(
                        codigoconsulta = doc.getString("codigoconsulta") ?: doc.id,
                        pacienteId = doc.getString("pacienteId") ?: "",
                        medicoCodigo = doc.getString("medicoCodigo") ?: "",
                        consultorioCodigo = doc.getString("consultorioCodigo") ?: "",
                        data = doc.getString("data") ?: ""
                    )
                    ListaGlobal.listaconsultas.add(c)
                }
                done()
            }
            .addOnFailureListener { e ->
                done()
            }
    }


    private fun carregarProfissionais(done: () -> Unit) {
        ListaGlobal.listaprofissional.clear()

        FirebaseManager.profissionaisRef().get()
            .addOnSuccessListener { snapshot ->
                for (doc in snapshot.documents) {

                    val tipo = (doc.getString("tipo") ?: "").trim()
                    val codigo = (doc.getString("codigo") ?: doc.id).trim()
                    val nome = (doc.getString("nome") ?: "").trim()

                    // tanto faz se no Firestore tens "contato" ou "contacto"
                    val contacto = (doc.getString("contacto") ?: doc.getString("contato") ?: "").trim()
                    val turno = (doc.getString("turno") ?: "").trim()

                    val obj: Profissional = when {
                        tipo.equals("Medico", true) || tipo.equals("Médico", true) ->
                            Medico(codigo, nome, contacto, turno)

                        else ->
                            Profissional(codigo, nome, contacto, turno, tipo = tipo)
                    }

                    ListaGlobal.listaprofissional.add(obj)
                }
                done()
            }
            .addOnFailureListener { done() }
    }

    private fun carregarSalas(done: () -> Unit) {
        ListaGlobal.listasalas.clear()

        FirebaseManager.salasRef().get()
            .addOnSuccessListener { snapshot ->
                for (doc in snapshot.documents) {

                    val tipo = (doc.getString("tipo") ?: "").trim()
                    val codigo = (doc.getString("codigo") ?: doc.id).trim()
                    val nome = (doc.getString("nome") ?: "").trim()
                    val quantidade = doc.getLong("quantidade")?.toInt() ?: 0

                    val sala: Sala = when {
                        tipo.equals("Consultorio", true) || tipo.equals("Consultório", true) ->
                            Consultorio(codigo, nome, quantidade)

                        tipo.equals("Enfermaria", true) ->
                            Enfermaria(codigo = codigo, nome = nome, tipo = "Enfermaria", quantidade = quantidade)

                        else ->
                            Sala(codigo, nome, tipo, quantidade)
                    }

                    ListaGlobal.listasalas.add(sala)
                }
                done()
            }
            .addOnFailureListener { done() }
    }


    private fun carregarInternamentos(done: () -> Unit) {
        ListaGlobal.listainternamentostotal.clear()

        FirebaseManager.internamentosRef().get()
            .addOnSuccessListener { snapshot ->
                snapshot.documents.forEach { doc ->
                    try {
                        val codigo = (doc.getString("codigo") ?: doc.id).trim()
                        val pacienteIdRaw = doc.getString("pacienteId") ?: ""
                        val enfermariaCodigoRaw = doc.getString("enfermariaCodigo") ?: ""
                        val dataEnt = doc.getString("dataEnt") ?: ""
                        val dataSaid = doc.getString("dataSaid") ?: ""
                        val estado = doc.getBoolean("estado") ?: true

                        // Normalizar códigos para comparações consistentes
                        val pacienteId = pacienteIdRaw.trim()
                        val enfermariaCodigo = enfermariaCodigoRaw.trim()

                        val intern = Internamento(
                            codigo = codigo,
                            pacienteId = pacienteId,
                            enfermariaCodigo = enfermariaCodigo,
                            dataEnt = dataEnt,
                            dataSaid = dataSaid,
                            estado = estado
                        )

                        ListaGlobal.listainternamentostotal.add(intern)
                    } catch (e: Exception) {
                        Log.e(TAG, "Erro a parsear internamento ${doc.id}", e)
                    }
                }

                // Relatório rápido (opcional)
                ListaGlobal.listasalas.filterIsInstance<Enfermaria>().forEach { e ->
                    val count = ListaGlobal.listainternamentostotal.count { it.enfermariaCodigo.trim().equals(e.codigo.trim(), ignoreCase = true) && it.estado }
                    Log.i(TAG, "Enfermaria '${e.codigo}' (${e.nome}) -> internamentos activos = $count")
                }

                done()
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Erro a carregar internamentos", e)
                done()
            }
    }
}
