package com.cibertec.tacosmarcial.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historial_pedidos")
data class HistorialPedido(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val usuarioId: Int,

    val nombrePlato: String,

    val cantidad: Int,

    val precio: Double,

    val fecha: String,

    val metodoPago: String = "Efectivo"
)