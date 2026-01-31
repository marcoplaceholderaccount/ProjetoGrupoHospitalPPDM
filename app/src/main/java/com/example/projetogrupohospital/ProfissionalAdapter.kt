package com.example.projetogrupohospital

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Usamos List<Profissional> para aceitar tanto Medicos quanto Enfermeiros
class ProfissionalAdapter(
    private val lista: List<Profissional>,
    private val onItemClick: (String) -> Unit // Função que recebe o código (String)
) : RecyclerView.Adapter<ProfissionalAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtCod: TextView = view.findViewById(R.id.txtCod)
        val txtNome: TextView = view.findViewById(R.id.txtNome)
        val txtCargo: TextView = view.findViewById(R.id.txtCargo)
        val txtTurno: TextView = view.findViewById(R.id.txtTurno)
        val txtContacto: TextView = view.findViewById(R.id.txtContacto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_profissional, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = lista[position]

        // Acessando propriedades da classe pai Profissional
        holder.txtCod.text = "Cód: ${p.codigo}"
        holder.txtNome.text = p.nome
        holder.txtTurno.text = "Turno: ${p.turno}"
        holder.txtContacto.text = "Tel: ${p.contacto}"

        // Determinando o Cargo com base no tipo da classe
        holder.txtCargo.text = if (p is Medico) "Cargo: Médico" else "Cargo: Enfermeiro"

        // Configura o toque no item inteiro
        holder.itemView.setOnClickListener {
            onItemClick(p.codigo) // Passa o código do profissional clicado
        }
    }

    override fun getItemCount() = lista.size
}