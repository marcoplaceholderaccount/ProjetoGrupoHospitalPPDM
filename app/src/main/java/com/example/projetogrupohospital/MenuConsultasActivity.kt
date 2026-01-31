package com.example.projetogrupohospital

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
        setContentView(R.layout.activity_menu_consulta)

    }
}