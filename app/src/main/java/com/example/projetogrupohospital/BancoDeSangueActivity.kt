package com.example.projetogrupohospital

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityBancoDeSangueBinding
import com.example.projetogrupohospital.databinding.ActivityEstatisticasBinding

class BancoDeSangueActivity : AppCompatActivity() {
    private lateinit var janela: ActivityBancoDeSangueBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityBancoDeSangueBinding.inflate(layoutInflater)
        setContentView(janela.root)

        janela.botaodoadoresdipo.setOnClickListener {
            val intent = Intent(this, DoadoresDisponiveisActivity::class.java)
            startActivity(intent)
        }

        janela.botaoregistardoadores.setOnClickListener {
            val intent = Intent(this, RegistoDoadorActivity::class.java)
            startActivity(intent)
        }

        janela.botaodoacao.setOnClickListener {
            val intent = Intent(this, DoacaoActivity::class.java)
            startActivity(intent)
        }

        var total = 00.00

        for(i in ListaGlobal.listadoacoes){
            total += i.quantidade
        }

        if(total < 1000.00){
            janela.txtestadobanco.setText("CRITICO")
        }else if (total>= 1000.00 && total < 1500.00){
            janela.txtestadobanco.setText("MÉDIO")
        }else{
            janela.txtestadobanco.setText("NORMAL")
        }

        janela.txtquantidadedispo.text = total.toString() + " L"


        janela.botaoutilizarsangue.setOnClickListener {
            var retirado= janela.campoquantidadeutilizado.text.toString().toDouble()


            if(retirado >total){
                Toast.makeText(this,"A quantidade é superior ao total!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                total -= retirado
                Toast.makeText(this,"Utilizado com sucesso!",Toast.LENGTH_SHORT).show()
            }


        }




    }
}