package com.pmdm.ieseljust.comptador

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log

import com.pmdm.ieseljust.comptador.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var comptador = 0
    var TAG = "Primera activitat"

    // Declarem l'objecte de vinculació
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicialitzem l'objecte de vinculació inflant la vista
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencia al TextView
        val textViewContador=findViewById<TextView>(R.id.textViewComptador)
        // Referencia al boto d'Open
        val btOpen=findViewById<Button>(R.id.btOpen)

        // Inicialitzem el TextView amb el comptador a 0
        textViewContador.text=comptador.toString() // Estem fent una assignacio directament o accedinta algun metode?

        // Referencia al botón de restar, añadido
        val btResta=findViewById<Button>(R.id.btResta)
        // Referencia al botón Reset añadido
        val btReset=findViewById<Button>(R.id.btReset)
        // Referencia al botón
        val btAdd=findViewById<Button>(R.id.btAdd)

        // Asociaciamos una expresión lambda como
        // respuesta (callback) al evento Clic sobre
        // el botón
        btAdd.setOnClickListener {
            comptador++
            // textViewContador.text=comptador.toString()
            binding.textViewComptador.text = comptador.toString()
        }

        // Asociaciamos una expresión lambda como
        // respuesta (callback) al evento Clic sobre
        // el botón
        btResta.setOnClickListener {
            comptador--
            // textViewContador.text=comptador.toString()
            binding.textViewComptador.text = comptador.toString()
        }

        // Asociaciamos una expresión lambda como
        // respuesta (callback) al evento Clic sobre
        // el botón
        btReset.setOnClickListener {
            comptador = 0
            // textViewContador.text=comptador.toString()
            binding.textViewComptador.text = comptador.toString()
        }

        /*btOpen.setOnClickListener{
            val intent = Intent(baseContext, MostraComptadorActivity::class.java)
            intent.putExtra("comptador", comptador)
            startActivity(intent)
        }*/

        btOpen.setOnClickListener {
            Intent(baseContext, MostraComptadorActivity::class.java).apply {
                putExtra("comptador", comptador)
                startActivity(this)
            }
        }

    }

    override fun onSaveInstanceState(estat: Bundle) {
        super.onSaveInstanceState(estat)
        estat.putInt("Comptador", comptador)
    }

    override fun onRestoreInstanceState(estat: Bundle) {
        super.onSaveInstanceState(estat)
        comptador = estat.getInt("Comptador")
        val textViewContador=findViewById<TextView>(R.id.textViewComptador)
        textViewContador.text=comptador.toString()
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