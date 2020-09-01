package com.llamasoftworks.moviles

import java.util.*

class PokemonHttp (
    var id:Int,
    var createdAt: Long,
    var updatedAt: Long,
    var nombre: String,
    var usuario: Int
){
    var fechaCreacion: Date
    var fechaActualizacion: Date

    init {
        fechaCreacion = Date(createdAt)
        fechaActualizacion = Date(updatedAt)
    }

}