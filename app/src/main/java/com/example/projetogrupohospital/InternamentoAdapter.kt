package com.example.projetogrupohospital

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InternamentoAdapter(
    private var lista: List<Internamento>,
    private val onItemClick: (Internamento) -> Unit
) : RecyclerView.Adapter<InternamentoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tvInternamento)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_internamento, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]

        // Procurar paciente pelo ID
        val pacienteNome =
            ListaGlobal.listapacientes
                .find { it.id == item.pacienteId }
                ?.nome ?: item.pacienteId

        holder.textView.text =
            "$pacienteNome - Código: ${item.codigo}"

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount() = lista.size

    fun atualizar(novaLista: List<Internamento>) {
        this.lista = novaLista
        notifyDataSetChanged()
    }
}
