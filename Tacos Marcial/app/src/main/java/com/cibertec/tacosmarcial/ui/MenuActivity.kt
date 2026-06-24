package com.cibertec.tacosmarcial.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.tacosmarcial.R
import com.cibertec.tacosmarcial.adapters.PlatoAdapter
import com.cibertec.tacosmarcial.models.DatosApp

class MenuActivity : AppCompatActivity() {

    private lateinit var btnCartButton: Button
    private lateinit var tvSucursal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_menu)

        tvSucursal = findViewById(R.id.tvSucursal)

        val rvTacos = findViewById<RecyclerView>(R.id.rvTacos)

        rvTacos.layoutManager =
            LinearLayoutManager(this)

        rvTacos.adapter =
            PlatoAdapter(
                DatosApp.menuPlatos.filter {
                    it.categoria == "Tacos"
                },
                { actualizarBotonCarrito() }
            )

        val rvEnchiladas =
            findViewById<RecyclerView>(R.id.rvEnchiladas)

        rvEnchiladas.layoutManager =
            LinearLayoutManager(this)

        rvEnchiladas.adapter =
            PlatoAdapter(
                DatosApp.menuPlatos.filter {
                    it.categoria == "Enchiladas"
                },
                { actualizarBotonCarrito() }
            )

        val rvBroasters =
            findViewById<RecyclerView>(R.id.rvBroasters)

        rvBroasters.layoutManager =
            LinearLayoutManager(this)

        rvBroasters.adapter =
            PlatoAdapter(
                DatosApp.menuPlatos.filter {
                    it.categoria == "Broasters"
                },
                { actualizarBotonCarrito() }
            )

        val rvHamburguesas =
            findViewById<RecyclerView>(R.id.rvHamburguesas)

        rvHamburguesas.layoutManager =
            LinearLayoutManager(this)

        rvHamburguesas.adapter =
            PlatoAdapter(
                DatosApp.menuPlatos.filter {
                    it.categoria == "Hamburguesas"
                },
                { actualizarBotonCarrito() }
            )

        val rvBebidas =
            findViewById<RecyclerView>(R.id.rvBebidas)

        rvBebidas.layoutManager =
            LinearLayoutManager(this)

        rvBebidas.adapter =
            PlatoAdapter(
                DatosApp.menuPlatos.filter {
                    it.categoria == "Bebidas"
                },
                { actualizarBotonCarrito() }
            )

        btnCartButton =
            findViewById(R.id.btnCartButton)

        btnCartButton.visibility = View.VISIBLE

        actualizarBotonCarrito()

        btnCartButton.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    ResumenCarritoActivity::class.java
                )
            )
        }
    }

    override fun onResume() {
        super.onResume()


        val nombre = DatosApp.lugarSeleccionado
        val direccion = DatosApp.direccionSeleccionada

        if (!nombre.isNullOrEmpty()) {
            tvSucursal.text = "$nombre\n$direccion"
        } else {
            tvSucursal.text = "Seleccione una sucursal"
        }

        actualizarBotonCarrito()
    }

    private fun actualizarBotonCarrito() {

        var total = 0.0
        var cantidadProductos = 0

        for (item in DatosApp.carrito) {

            total +=
                item.plato.precio * item.cantidad

            cantidadProductos +=
                item.cantidad
        }

        btnCartButton.text =
            "🛒 $cantidadProductos productos • S/ ${
                String.format(java.util.Locale.US, "%.2f", total)
            }"
    }
}