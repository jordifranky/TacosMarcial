package com.cibertec.tacosmarcial.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pedidos_locales")
data class Pedido(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idPlato: Int,
    val nombrePlato: String,
    val precio: Double,
    val cantidad: Int,
    val estadoSincronizacion: Int = 0,
    val tipoEntrega: String = "Domicilio",
    val detalleLugar: String = "No especificado"
)