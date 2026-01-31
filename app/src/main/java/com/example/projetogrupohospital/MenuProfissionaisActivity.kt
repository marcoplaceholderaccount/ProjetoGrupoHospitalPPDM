package com.example.projetogrupohospital

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityMenuProfissionaisBinding

class MenuProfissionaisActivity : AppCompatActivity() {
    private lateinit var janela : ActivityMenuProfissionaisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityMenuProfissionaisBinding.inflate(layoutInflater)
        setContentView(janela.root)

        val turnos = listOf<String>("Diurno","Noturno")
        val adapterT = ArrayAdapter(
            this,
            R.layout.simple_spinner_item,
            turnos)
        janela.profTurno.adapter = adapterT

        val cod = janela.profCod
        val nome = janela.profNome
        val contacto = janela.profCont
        val turno = janela.profTurno.selectedItem.toString()


        janela.btnConfirmar.setOnClickListener {




        }

    }
}