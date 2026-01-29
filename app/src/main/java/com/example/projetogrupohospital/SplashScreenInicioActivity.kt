package com.example.projetogrupohospital

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projetogrupohospital.databinding.ActivityGestaoSalaBinding
import com.example.projetogrupohospital.databinding.ActivitySplashScreenInicioBinding

class SplashScreenInicioActivity : AppCompatActivity() {
    private lateinit var janela: ActivitySplashScreenInicioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        janela = ActivitySplashScreenInicioBinding.inflate(layoutInflater)
        setContentView(janela.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MenuInicialActivity::class.java)
            startActivity(intent)
        }, 3000);

    }
}