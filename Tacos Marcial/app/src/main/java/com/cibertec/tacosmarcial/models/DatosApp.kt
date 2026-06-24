package com.cibertec.tacosmarcial.models

object DatosApp {

    // Todas las sedes reales en SJL
    val listaSucursales = listOf(
        Sucursal(1, "Sede Los Jardines", "Av. Los Jardines Oeste 15419", "15:30 - 23:50", -12.0028, -77.0055),
        Sucursal(2, "Sede Mall Aventura SJL", "Av. Lurigancho 997, Sótano 1", "10:00 - 22:00", -12.0165, -77.0005),
        Sucursal(3, "Sede Canto Grande", "Av. Canto Grande (Principal)", "15:30 - 23:50", -11.9754, -77.0001),
        Sucursal(4, "Sede Canto Rey", "Av. Wiesse / Canto Rey", "15:30 - 23:50", -11.9564, -77.0012),
        Sucursal(5, "Sede Bayóvar", "Av. Bayóvar Principal", "15:30 - 23:50", -11.9288, -76.9934),
        Sucursal(6, "Sede Restobar", "San Juan de Lurigancho Centro", "17:00 - 02:00", -11.9854, -77.0101)
    )

    // Carta completa de Tacos Marcial
    val menuPlatos = listOf(
        // TACOS
        Plato(1, "Tacos", "Taco Chavo", "Pollo deshilachado, frijoles y guacamole.", 16.0),
        Plato(2, "Tacos", "Taco Don Ramón", "Chorizo, frijoles y guacamole.", 16.0),
        Plato(3, "Tacos", "Taco Ñoño", "Pollo, chorizo, frijoles, guacamole y papas.", 17.0),
        Plato(4, "Tacos", "Taco Don Barriga", "Pollo, chorizo, queso, huevo, frijoles y guacamole.", 20.0),
        Plato(5, "Tacos", "Taco Huachana Especial", "Pollo, salchicha huachana, huevo, frijoles y guacamole.", 21.0),
        Plato(6, "Tacos", "Taco Jiraleafs", "Pollo, chorizo, huevo, queso, hot dog, frijoles, guacamole y papas.", 21.0),
        Plato(7, "Tacos", "Súper Taco", "Pollo, chorizo, huevo, queso, tocino, cabanossi, hot dog, frijoles y guacamole.", 25.0),
        Plato(8, "Tacos", "Tacos al Pastor", "Cerdo, piña, cebolla y culantro.", 25.0),

        // ENCHILADAS
        Plato(9, "Enchiladas", "Enchilada Doña Florinda", "Pollo, jamón y queso fundido.", 16.0),
        Plato(10, "Enchiladas", "Enchilada Chilindrina", "Jamón, queso y chorizo.", 16.0),
        Plato(11, "Enchiladas", "Enchilada Popis", "Jamón, queso, pollo y chorizo.", 18.0),
        Plato(12, "Enchiladas", "Enchilada Twister", "Queso, pechuga empanizada y tocino.", 19.0),
        Plato(13, "Enchiladas", "Enchilada Ranchera", "Jamón, queso, pollo, chorizo y huevo.", 19.0),
        Plato(14, "Enchiladas", "Enchilada Huachana", "Jamón, queso, pollo, huevo y salchicha huachana.", 19.0),

        // BROASTERS
        Plato(15, "Broasters", "Broaster Clásico", "Pieza de pollo crujiente con papas fritas y ensalada.", 16.0),
        Plato(16, "Broasters", "Alitas Broaster", "6 alitas crujientes con papas y cremas de la casa.", 18.0),

        // HAMBURGUESAS
        Plato(17, "Hamburguesas", "Hamburguesa Royal", "Carne de res, huevo frito, queso y vegetales frescos.", 14.0),
        Plato(18, "Hamburguesas", "Hamburguesa Doble", "Doble carne de res, doble queso cheddar y tocino.", 19.0),

        // BEBIDAS
        Plato(19, "Bebidas", "Agua de Jamaica", "Bebida refrescante tradicional mexicana.", 5.0),
        Plato(20, "Bebidas", "Limonada Frozen", "Limonada granizada ideal para acompañar tus tacos.", 7.0)
    )

    // Carrito de compras
    val carrito = mutableListOf<CarritoItem>()


    var tipoEntrega: String = "Domicilio"
    var lugarSeleccionado: String = "No especificado"
    var direccionSeleccionada: String = ""

    fun obtenerPlatosPorCategoria(categoria: String): List<Plato> {
        return menuPlatos.filter {
            it.categoria.equals(categoria, true)
        }
    }
}