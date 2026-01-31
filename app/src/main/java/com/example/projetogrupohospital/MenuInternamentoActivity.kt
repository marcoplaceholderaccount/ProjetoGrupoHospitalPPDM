package com.example.projetogrupohospital

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityMainBinding
import com.example.projetogrupohospital.databinding.ActivityMenuInternamentoBinding

class MenuInternamentoActivity : AppCompatActivity() {
    private lateinit var janela : ActivityMenuInternamentoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityMenuInternamentoBinding.inflate(layoutInflater)
        setContentView(janela.root)

        janela.btnRegistar.setOnClickListener {
            val intent = Intent(this, GestaoInternamentoActivity::class.java)
            startActivity(intent)
        }

    }
}