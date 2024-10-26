package com.pmdm.ieseljust.comptador

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MostraComptadorActivity : AppCompatActivity() {
    var TAG = "Segona activitat"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mostra_comptador)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencia al TextView
        val textViewContador=findViewById<TextView>(R.id.textViewMostraComptador)
        // Referencia al boto d'Open
        val btBack=findViewById<Button>(R.id.btBack)

        // Agafem la informacio de la Intent
        val comptador:Int? = intent.getIntExtra("comptador", 0)

        // Inicialitzem el TextView amb el comptador a 0
        textViewContador.text=comptador.toString()


        // Asociaciamos una expresióin lambda como
        // respuesta (callback) al evento Clic sobre
        // el botón
        btBack.setOnClickListener {
            finish()
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, TAG+": Al mètode onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, TAG+": Al mètode onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, TAG+": Al mètode onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, TAG+": Al mètode onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, TAG+": Al mètode onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, TAG+": Al mètode onDestroy")
    }

}