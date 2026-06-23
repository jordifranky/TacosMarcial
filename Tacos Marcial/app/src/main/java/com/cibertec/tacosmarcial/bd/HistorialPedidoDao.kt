package com.cibertec.tacosmarcial.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cibertec.tacosmarcial.models.HistorialPedido

@Dao
interface HistorialPedidoDao {

    @Insert
    suspend fun insertar(
        historialPedido: HistorialPedido
    )

    @Query(
        """
        SELECT * FROM historial_pedidos
        WHERE usuarioId = :usuarioId
        """
    )
    suspend fun listarPorUsuario(
        usuarioId: Int
    ): List<HistorialPedido>

    @Query("SELECT * FROM historial_pedidos")
    suspend fun obtenerTodo(): List<HistorialPedido>

    @Query("DELETE FROM historial_pedidos")
    suspend fun eliminarTodo()
}