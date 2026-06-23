package com.cibertec.tacosmarcial.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cibertec.tacosmarcial.models.Usuario

@Dao
interface UsuarioDao {

    @Insert
    suspend fun insertar(usuario: Usuario)

    @Query("""
        SELECT * FROM usuarios
        WHERE email = :email
        LIMIT 1
    """)
    suspend fun buscarPorEmail(email: String): Usuario?

    @Query("""
        SELECT * FROM usuarios
        WHERE email = :email
        AND password = :password
        LIMIT 1
    """)
    suspend fun login(
        email: String,
        password: String
    ): Usuario?
}