package com.cibertec.tacosmarcial.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.tacosmarcial.R
import com.cibertec.tacosmarcial.adapters.CarritoAdapter
import com.cibertec.tacosmarcial.db.AppDatabase
import com.cibertec.tacosmarcial.models.DatosApp
import com.cibertec.tacosmarcial.models.Pedido
import kotlinx.coroutines.launch
import com.cibertec.tacosmarcial.models.HistorialPedido

class CarritoActivity : AppCompatActivity() {

    private lateinit var txtTotal: TextView
    private lateinit var txtCarritoVacio: TextView
    private lateinit var rvCarrito: RecyclerView
    private lateinit var btnConfirmar: Button
    private lateinit var rgMetodoPago: RadioGroup
    private lateinit var cbTerminos: CheckBox
    private lateinit var tvClienteNombre: TextView
    private lateinit var tvDireccionEntrega: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_carrito)

        txtTotal = findViewById(R.id.txtTotal)
        txtCarritoVacio = findViewById(R.id.txtCarritoVacio)
        rvCarrito = findViewById(R.id.rvCarrito)
        btnConfirmar = findViewById(R.id.btnConfirmar)
        rgMetodoPago = findViewById(R.id.rgMetodoPago)
        cbTerminos = findViewById(R.id.cbTerminos)
        tvClienteNombre = findViewById(R.id.tvClienteNombre)
        tvDireccionEntrega = findViewById(R.id.tvDireccionEntrega)

        // Cargar datos reales de sesión y selección
        val sessionManager = com.cibertec.tacosmarcial.core.SessionManager(this)
        tvClienteNombre.text = sessionManager.getUserName() ?: "Cliente"
        tvDireccionEntrega.text = if (DatosApp.tipoEntrega == "Recojo en Tienda") 
            "Recojo: ${DatosApp.lugarSeleccionado}" 
            else "Entrega: ${DatosApp.direccionSeleccionada}"

        findViewById<ImageView>(R.id.btnBack).setOnClickListener { finish() }

        rvCarrito.layoutManager = LinearLayoutManager(this)
        rvCarrito.adapter = CarritoAdapter(DatosApp.carrito) { actualizarVista() }

        actualizarVista()

        // Lógica de Términos y Condiciones: Bloquear botón si no acepta
        btnConfirmar.isEnabled = false
        btnConfirmar.alpha = 0.5f

        cbTerminos.setOnCheckedChangeListener { _, isChecked ->
            btnConfirmar.isEnabled = isChecked
            btnConfirmar.alpha = if (isChecked) 1.0f else 0.5f
        }

        btnConfirmar.setOnClickListener {
            guardarPedidoEnHistorial()
        }
    }

    private fun guardarPedidoEnHistorial() {
        val selectedId = rgMetodoPago.checkedRadioButtonId
        val radioButton = findViewById<RadioButton>(selectedId)
        val metodoPagoSeleccionado = radioButton.text.toString()

        lifecycleScope.launch {
            try {
                val db = AppDatabase.getDatabase(this@CarritoActivity)

                for (item in DatosApp.carrito) {

                    val nuevoPedido = Pedido(
                        idPlato = item.plato.id,
                        nombrePlato = item.plato.nombre,
                        precio = item.plato.precio,
                        cantidad = item.cantidad,
                        tipoEntrega = DatosApp.tipoEntrega,
                        detalleLugar = DatosApp.lugarSeleccionado
                    )

                    db.pedidoDao().insertarPedido(nuevoPedido)

                    val historial = HistorialPedido(
                        usuarioId = 1,
                        nombrePlato = item.plato.nombre,
                        cantidad = item.cantidad,
                        precio = item.plato.precio,
                        fecha = java.text.SimpleDateFormat(
                            "dd/MM/yyyy HH:mm",
                            java.util.Locale.getDefault()
                        ).format(java.util.Date()),
                        metodoPago = metodoPagoSeleccionado
                    )

                    db.historialPedidoDao().insertar(historial)
                }





                // Limpiamos la memoria y navegamos
                DatosApp.carrito.clear()

                val proximaActivity = if (DatosApp.tipoEntrega == "A domicilio") {
                    SeguimientoPedidoActivity::class.java
                } else {
                    PedidoExitosoActivity::class.java
                }

                val intent = Intent(this@CarritoActivity, proximaActivity)
                startActivity(intent)
                finish()

            } catch (e: Exception) {
                Toast.makeText(this@CarritoActivity, "Error al guardar: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun actualizarVista() {
        if (DatosApp.carrito.isEmpty()) {
            txtCarritoVacio.visibility = View.VISIBLE
            rvCarrito.visibility = View.GONE
            txtTotal.visibility = View.GONE
            btnConfirmar.visibility = View.GONE
        } else {
            txtCarritoVacio.visibility = View.GONE
            rvCarrito.visibility = View.VISIBLE
            txtTotal.visibility = View.VISIBLE
            btnConfirmar.visibility = View.VISIBLE
            actualizarTotal()
        }
    }

    private fun actualizarTotal() {
        var total = 0.0
        for (item in DatosApp.carrito) {
            total += item.plato.precio * item.cantidad
        }
        txtTotal.text = "S/ ${String.format("%.2f", total)}"
    }
}