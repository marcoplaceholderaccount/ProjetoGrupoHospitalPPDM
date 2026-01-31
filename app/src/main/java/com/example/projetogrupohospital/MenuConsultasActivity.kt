package com.example.projetogrupohospital

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.projetogrupohospital.databinding.ActivityMenuConsultaBinding

class MenuConsultasActivity : AppCompatActivity() {

    private lateinit var janela: ActivityMenuConsultaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityMenuConsultaBinding.inflate(layoutInflater)
        setContentView(janela.root)

        janela.btnConsulta.setOnClickListener{
            val intent = Intent(this, ConsultaActivity::class.java)
            startActivity(intent)
        }

        janela.btnListar.setOnClickListener {
            val intent = Intent(this, ListarConsultasActivity::class.java)
            startActivity(intent)
        }


    }
}