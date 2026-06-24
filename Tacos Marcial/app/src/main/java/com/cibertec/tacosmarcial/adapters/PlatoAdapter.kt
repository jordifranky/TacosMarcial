package com.cibertec.tacosmarcial.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.tacosmarcial.R
import com.cibertec.tacosmarcial.models.CarritoItem
import com.cibertec.tacosmarcial.models.DatosApp
import com.cibertec.tacosmarcial.models.Plato
import com.cibertec.tacosmarcial.ui.DetallePlatoActivity

class PlatoAdapter(
    private val listaPlatos: List<Plato>,
    private val onPlatoAgregado: () -> Unit = {}
) :
    RecyclerView.Adapter<PlatoAdapter.PlatoViewHolder>() {

    class PlatoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombrePlato: TextView = itemView.findViewById(R.id.tvNombrePlato)
        val tvPrecioPlato: TextView = itemView.findViewById(R.id.tvPrecioPlato)
        val tvDescPlato: TextView = itemView.findViewById(R.id.tvDescPlato)
        val btnAgregar: Button = itemView.findViewById(R.id.btnAgregar)
        val imgPlato: ImageView = itemView.findViewById(R.id.imgPlato)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_plato, parent, false)
        return PlatoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlatoViewHolder, position: Int) {
        val plato = listaPlatos[position]

        holder.tvNombrePlato.text = plato.nombre
        holder.tvPrecioPlato.text = "S/ ${String.format("%.2f", plato.precio)}"
        holder.tvDescPlato.text = plato.descripcion


        val imagenRes = when (plato.categoria) {
            "Tacos" -> R.drawable.taco
            "Enchiladas" -> R.drawable.enchiladas
            "Broasters" -> R.drawable.broasters
            "Hamburguesas" -> R.drawable.hamburguesas
            "Bebidas" -> R.drawable.bebidas
            else -> R.drawable.taco
        }
        holder.imgPlato.setImageResource(imagenRes)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetallePlatoActivity::class.java).apply {
                putExtra("PLATO_ID", plato.id)
                putExtra("PLATO_NOMBRE", plato.nombre)
                putExtra("PLATO_PRECIO", plato.precio)
                putExtra("PLATO_DESCRIPCION", plato.descripcion)
                putExtra("PLATO_CATEGORIA", plato.categoria)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.btnAgregar.setOnClickListener {
            val itemExistente = DatosApp.carrito.find { it.plato.id == plato.id }
            if (itemExistente != null) {
                itemExistente.cantidad++
            } else {
                DatosApp.carrito.add(CarritoItem(plato = plato, cantidad = 1))
            }
            onPlatoAgregado()
            Toast.makeText(
                holder.itemView.context,
                "${plato.nombre} (S/ ${String.format("%.2f", plato.precio)}) agregado",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int = listaPlatos.size
}