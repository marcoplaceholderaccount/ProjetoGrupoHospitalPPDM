package com.example.projetogrupohospital

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityListaProfissionaisBinding

class ListaProfissionaisActivity : AppCompatActivity() {
    private lateinit var janela : ActivityListaProfissionaisBinding
    private lateinit var adapterProf: ProfissionalAdapter // Declarar o adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityListaProfissionaisBinding.inflate(layoutInflater)
        setContentView(janela.root)

        // Dentro do onCreate da ListaProfissionaisActivity.kt
        adapterProf = ProfissionalAdapter(ListaGlobal.listaprofissional) { codigoClicado ->
            // Quando clicar, abre a tela de edição passando o código
            val intent = Intent(this, EditarProfissionaisActivity::class.java)
            intent.putExtra("CODIGO_EXTRA", codigoClicado)
            startActivity(intent)
        }
        janela.recyclerFuncionarios.adapter = adapterProf

    }
}