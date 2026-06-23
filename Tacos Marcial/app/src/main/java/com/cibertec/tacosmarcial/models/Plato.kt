package com.cibertec.tacosmarcial.models

data class Plato(
    val id: Int,
    val categoria: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double
)