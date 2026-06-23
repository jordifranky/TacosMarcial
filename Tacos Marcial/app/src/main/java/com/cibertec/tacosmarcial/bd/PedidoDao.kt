package com.cibertec.tacosmarcial.db

import androidx.room.*
import com.cibertec.tacosmarcial.models.Pedido

@Dao
interface PedidoDao {

    @Insert
    suspend fun insertarPedido(pedido: Pedido)

    @Update
    suspend fun actualizarPedido(pedido: Pedido)

    @Delete
    suspend fun eliminarPedido(pedido: Pedido)

    @Query("SELECT * FROM pedidos_locales")
    suspend fun listarTodos(): List<Pedido>

    @Query("SELECT * FROM pedidos_locales WHERE id = :id")
    suspend fun buscarPorId(id: Int): Pedido?

    @Query("DELETE FROM pedidos_locales")
    suspend fun eliminarTodos()

    @Query("SELECT * FROM pedidos_locales WHERE estadoSincronizacion = 0")
    suspend fun obtenerPendientes(): List<Pedido>

    @Query("""
        UPDATE pedidos_locales
        SET estadoSincronizacion = 1
        WHERE id = :id
    """)
    suspend fun marcarSincronizado(id: Int)
}