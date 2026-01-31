package com.example.projetogrupohospital

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityMenuProfissionalBinding

class MenuProfissionalActivity : AppCompatActivity() {
    private lateinit var janela : ActivityMenuProfissionalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityMenuProfissionalBinding.inflate(layoutInflater)
        setContentView(janela.root)


        janela.btnRegistar.setOnClickListener {
            val intent = Intent(this, RegistarProfissionaisActivity::class.java)
            startActivity(intent)
        }

        janela.btnListar.setOnClickListener {
            val intent = Intent(this, ListaProfissionaisActivity::class.java)
            startActivity(intent)
        }


    }
}