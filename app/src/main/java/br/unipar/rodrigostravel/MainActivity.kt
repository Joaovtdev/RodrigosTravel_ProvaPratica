package br.unipar.rodrigostravel

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private val listaDeViagens = mutableListOf<ViagensAdapter>()
    private lateinit var adapter : AdapterLista

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edDestino = findViewById<EditText>(R.id.edDestino)
        val edGasto = findViewById<EditText>(R.id.edGasto)
        val btnInserir = findViewById<Button>(R.id.btnInserir)
        val listViewViagens = findViewById<ListView>(R.id.listViagens)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        var totalGasto = 0.0
        var contViagens = 0

        adapter = AdapterLista(this,listaDeViagens)
        // Vinculando o meu Adapter com a minha View
        listViewViagens.adapter = adapter

        btnInserir.setOnClickListener{
            val destino = edDestino.text.toString()
            val gastoTeste = edGasto.text.toString()
            val gasto: Double
            if (gastoTeste.isNotEmpty()) {
                gasto = edGasto.text.toString().toDouble()
            } else {
                gasto = 0.0
            }
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            val data = Calendar.getInstance().time
            val dataFormatada = dateFormat.format(data)

            if ((destino.isNotEmpty()) and (gastoTeste.isNotEmpty())){
                listaDeViagens.add(ViagensAdapter("Destino: "+destino,"Data: "+dataFormatada,"Valor Gasto: R$"+gasto))
                adapter.notifyDataSetChanged()

                edDestino.text?.clear()
                edGasto.text?.clear()
                contViagens++
                totalGasto = totalGasto + gasto
                tvTotal.text = "Total Gasto: R$" + totalGasto
            } else {
                Toast.makeText(this, "Preencha os campos!", Toast.LENGTH_SHORT).show()
            }
    }
}}