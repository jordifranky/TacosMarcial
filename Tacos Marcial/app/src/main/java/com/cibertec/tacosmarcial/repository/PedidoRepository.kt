package com.cibertec.tacosmarcial.repository

import com.cibertec.tacosmarcial.db.PedidoDao
import com.cibertec.tacosmarcial.models.Pedido

class PedidoRepository(
    private val pedidoDao: PedidoDao
) {

    suspend fun insertar(pedido: Pedido) {
        pedidoDao.insertarPedido(pedido)
    }

    suspend fun actualizar(pedido: Pedido) {
        pedidoDao.actualizarPedido(pedido)
    }

    suspend fun eliminar(pedido: Pedido) {
        pedidoDao.eliminarPedido(pedido)
    }

    suspend fun listarTodos(): List<Pedido> {
        return pedidoDao.listarTodos()
    }

    suspend fun obtenerPendientes(): List<Pedido> {
        return pedidoDao.obtenerPendientes()
    }

    suspend fun eliminarTodos() {
        pedidoDao.eliminarTodos()
    }

    suspend fun marcarSincronizado(id: Int) {
        pedidoDao.marcarSincronizado(id)
    }

}