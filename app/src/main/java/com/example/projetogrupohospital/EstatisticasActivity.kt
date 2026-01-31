package com.example.projetogrupohospital

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityEstatisticasBinding

class EstatisticasActivity : AppCompatActivity() {
    private lateinit var janela: ActivityEstatisticasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityEstatisticasBinding.inflate(layoutInflater)
        setContentView(janela.root)

        atualizarDashboard()

    }
        private fun atualizarDashboard() {

            //retorna o tamanho de cada lista e mostra nas estatiscas
            janela.totalP.text = ListaGlobal.listapacientes.size.toString()
            janela.totalF.text = ListaGlobal.listaprofissional.size.toString()
            janela.totalS.text = ListaGlobal.listasalas.size.toString()
            janela.totalR.text= ListaGlobal.listaconsultas.size.toString()
            janela.totalIR.text= ListaGlobal.listainternamentostotal.size.toString()
            janela.totalD.text= ListaGlobal.listadoadores.size.toString()
            janela.totalDR.text= ListaGlobal.listadoacoes.size.toString()



        }

}