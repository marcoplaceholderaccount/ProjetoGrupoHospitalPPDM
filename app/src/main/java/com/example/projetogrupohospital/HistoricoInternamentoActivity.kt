package com.example.projetogrupohospital

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityHistoricoInternamentoBinding

class HistoricoInternamentoActivity : AppCompatActivity() {
    private lateinit var janela : ActivityHistoricoInternamentoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityHistoricoInternamentoBinding.inflate(layoutInflater)
        setContentView(janela.root)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            ListaGlobal.listainternamentostotal
        )

        janela.lista.adapter = adapter
    }
}