package com.cibertec.tacosmarcial.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cibertec.tacosmarcial.models.HistorialPedido
import com.cibertec.tacosmarcial.models.Pedido
import com.cibertec.tacosmarcial.models.Usuario

@Database(
    entities = [
        Pedido::class,
        Usuario::class,
        HistorialPedido::class
    ],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pedidoDao(): PedidoDao

    abstract fun usuarioDao(): UsuarioDao

    abstract fun historialPedidoDao(): HistorialPedidoDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tacos_marcial_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}