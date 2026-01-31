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

        val cargos = listOf<String>("Medico","Enfermeiro")
        val adapterC = ArrayAdapter(
            this,
            R.layout.simple_spinner_item,
            cargos
        )
        janela.cargo.adapter = adapterC

        janela.btnConfirmar.setOnClickListener {

            val cod = janela.profCod.text.toString()
            val nome = janela.profNome.text.toString()
            val contacto = janela.profCont.text.toString()
            val turno = janela.profTurno.selectedItem.toString()
            val cargo = janela.cargo.selectedItem.toString()

            if (cod.isNotEmpty() && nome.isNotEmpty() && contacto.isNotEmpty()) {
                if (cargo == "Medico") {
                    val med = Medico(cod, nome, contacto, turno)
                    ListaGlobal.listaprofissional.add(med)
                } else {
                    val enf = Enfermeiro(cod, nome, contacto, turno)
                    ListaGlobal.listaprofissional.add(enf)
                }

                // Limpar os campos para a próxima entrada
                janela.profCod.text.clear()
                janela.profNome.text.clear()
                janela.profCont.text.clear()

            }
        }




    }
}