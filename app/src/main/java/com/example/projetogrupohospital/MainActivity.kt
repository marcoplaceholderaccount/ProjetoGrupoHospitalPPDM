package com.example.projetogrupohospital

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
    private lateinit var janela : ActivityMainBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivityMainBinding.inflate(layoutInflater)
        setContentView(janela.root)

        // INICIALIZAÇÃO (Obrigatório ser aqui)
        auth = Firebase.auth
        db = Firebase.firestore

        // BOTÃO ENTRAR
        janela.btnEntrar.setOnClickListener {
            val email = janela.user.text.toString()
            val password = janela.pass.text.toString()


            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@MainActivity, "Conta Criada!", Toast.LENGTH_SHORT).show()
                        var i = Intent(this, MenuProfissionaisActivity::class.java)
                        startActivity(i)
                    } else {
                        Toast.makeText(this@MainActivity, "Erro no Registro!", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // BOTÃO REGISTAR (Só Auth)
        janela.btnRegistar.setOnClickListener {
            val email = janela.user.text.toString()
            val password = janela.pass.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@MainActivity, "Conta Criada!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "Erro no Registro!", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // BOTÃO GUARDAR (Só DB)
        janela.btnGuardar.setOnClickListener {
            val email = janela.user.text.toString()
            val password = janela.pass.text.toString()

            val user = hashMapOf(
                "email" to email,
                "password" to password
            )

            db.collection("users")
                .add(user)
                .addOnSuccessListener { doc ->
                    Toast.makeText(this@MainActivity, "Guardado! ID: ${doc.id}", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this@MainActivity, "Erro ao Guardar!", Toast.LENGTH_SHORT).show()
                }
        }
    }
}