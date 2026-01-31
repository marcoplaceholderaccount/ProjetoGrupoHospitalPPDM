package com.example.projetogrupohospital

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.projetogrupohospital.databinding.ActivityBancoDeSangueBinding

class BancoDeSangueActivity : AppCompatActivity() {

    private lateinit var janela: ActivityBancoDeSangueBinding
    private var total = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityBancoDeSangueBinding.inflate(layoutInflater)
        setContentView(janela.root)

        // ===============================
        // BOTÕES DE NAVEGAÇÃO
        // ===============================
        janela.botaodoadoresdipo.setOnClickListener {
            startActivity(Intent(this, DoadoresDisponiveisActivity::class.java))
        }

        janela.botaoregistardoadores.setOnClickListener {
            startActivity(Intent(this, RegistoDoadorActivity::class.java))
        }

        janela.botaodoacao.setOnClickListener {
            startActivity(Intent(this, DoacaoActivity::class.java))
        }

        // ===============================
        // BOTÃO UTILIZAR SANGUE
        // ===============================
        janela.botaoutilizarsangue.setOnClickListener {

            val texto = janela.campoquantidadeutilizado.text.toString()

            if (texto.isBlank()) {
                Toast.makeText(this, "Insira uma quantidade!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val retirado = texto.toDouble()

            if (retirado > total) {
                Toast.makeText(
                    this,
                    "A quantidade é superior ao total!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            total -= retirado

            janela.txtquantidadedispo.text = "$total mL"
            atualizarEstadoBanco(total)

            Toast.makeText(
                this,
                "Utilizado com sucesso!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // atualiza total ao voltar a tela
    override fun onResume() {
        super.onResume()
        atualizarTotal()
    }

    // recalcular total
    private fun atualizarTotal() {
        total = 0.0

        for (i in ListaGlobal.listadoacoes) {
            total += i.quantidade
        }

        janela.txtquantidadedispo.text = "$total mL"
        atualizarEstadoBanco(total)
    }

    // ===============================
    // ESTADO DO BANCO
    // ===============================
    private fun atualizarEstadoBanco(total: Double) {
        if (total < 1000.0) {
            janela.txtestadobanco.text = "CRITICO"
        } else if (total < 1500.0) {
            janela.txtestadobanco.text = "MÉDIO"
        } else {
            janela.txtestadobanco.text = "NORMAL"
        }
    }
}
