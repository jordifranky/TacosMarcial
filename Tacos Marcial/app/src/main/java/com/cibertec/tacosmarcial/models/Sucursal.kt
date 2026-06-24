package com.cibertec.tacosmarcial.models

data class Sucursal(
    val id: Int,
    val nombreLocal: String,
    val direccion: String,
    val horario: String,
    val lat: Double = 0.0,
    val lng: Double = 0.0
)