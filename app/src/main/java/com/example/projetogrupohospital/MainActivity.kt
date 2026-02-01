package com.example.projetogrupohospital

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.projetogrupohospital.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {

    private lateinit var janela: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private var dadosCarregados = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        janela = ActivityMainBinding.inflate(layoutInflater)
        setContentView(janela.root)

        auth = Firebase.auth
        db = Firebase.firestore

        // Carregar dados globais
        DataLoader.carregarTudo {
            dadosCarregados = true
            Toast.makeText(this, "Dados carregados com sucesso!", Toast.LENGTH_SHORT).show()
        }

        // ---------- LOGIN ----------
        janela.btnEntrar.setOnClickListener {

            if (!dadosCarregados) {
                Toast.makeText(this, "A carregar dados... aguarde.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val email = janela.user.text.toString()
            val password = janela.pass.text.toString()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login efectuado!", Toast.LENGTH_SHORT).show()

                        val i = Intent(this, MenuInicialActivity::class.java)
                        startActivity(i)
                        finish()
                    } else {
                        Toast.makeText(this, "Erro no login!", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // ---------- REGISTAR ----------
        janela.btnRegistar.setOnClickListener {
            val email = janela.user.text.toString()
            val password = janela.pass.text.toString()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Conta criada!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Erro ao registar!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
