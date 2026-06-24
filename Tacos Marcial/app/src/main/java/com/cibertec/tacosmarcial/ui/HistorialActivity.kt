package com.cibertec.tacosmarcial.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.tacosmarcial.R
import com.cibertec.tacosmarcial.adapters.HistorialAdapter
import com.cibertec.tacosmarcial.db.AppDatabase
import com.cibertec.tacosmarcial.models.HistorialPedido
import kotlinx.coroutines.launch

class HistorialActivity : AppCompatActivity() {

    private val tag = "HISTORIAL_DEBUG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        val rvHistorial = findViewById<RecyclerView>(R.id.rvHistorial)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)

        rvHistorial.layoutManager = LinearLayoutManager(this)

        cargarHistorial(rvHistorial)

        btnLimpiar.setOnClickListener {
            limpiarHistorial(rvHistorial)
        }
    }

    private fun limpiarHistorial(recyclerView: RecyclerView) {
        lifecycleScope.launch {
            try {
                val db = AppDatabase.getDatabase(this@HistorialActivity)
                db.historialPedidoDao().eliminarTodo()
                Toast.makeText(this@HistorialActivity, "Historial limpiado", Toast.LENGTH_SHORT).show()
                cargarHistorial(recyclerView)
            } catch (e: Exception) {
                Log.e(tag, "Error al limpiar: ${e.message}")
                Toast.makeText(this@HistorialActivity, "Error al limpiar: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun cargarHistorial(recyclerView: RecyclerView) {
        lifecycleScope.launch {
            try {
                val db = AppDatabase.getDatabase(this@HistorialActivity)
                val lista = db.historialPedidoDao().obtenerTodo()

                recyclerView.adapter = HistorialAdapter(lista.reversed())
            } catch (e: Exception) {
                Log.e(tag, "Error al cargar: ${e.message}")
                Toast.makeText(this@HistorialActivity, "Error al cargar: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}