import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetogrupohospital.Internamento

class InternamentoAdapter(
    private var lista: List<Internamento>,
    private val onItemClick: (Internamento) -> Unit
) : RecyclerView.Adapter<InternamentoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]
        holder.textView.text = "${item.paciente.nome} - Código: ${item.codigo}"
        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    override fun getItemCount() = lista.size

    // Função para atualizar a lista do adapter quando filtrarmos
    fun atualizar(novaLista: List<Internamento>) {
        this.lista = novaLista
        notifyDataSetChanged()
    }
}