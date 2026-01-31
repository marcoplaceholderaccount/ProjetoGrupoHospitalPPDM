package com.example.projetogrupohospital

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityServicosBinding

class ServicosActivity : AppCompatActivity() {

    private lateinit var janela: ActivityServicosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        janela= ActivityServicosBinding.inflate(layoutInflater)
        setContentView(janela.root)

        janela.btnConsulta.setOnClickListener {

            val intent = Intent(this, MenuConsultasActivity::class.java)
            startActivity(intent)
        }



    }
}