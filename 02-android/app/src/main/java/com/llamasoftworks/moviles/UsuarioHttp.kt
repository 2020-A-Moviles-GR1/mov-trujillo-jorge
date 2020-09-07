package com.llamasoftworks.moviles

import java.util.*
import kotlin.collections.ArrayList

class UsuarioHttp(
    var id:Int,
    var createdAt:Long,
    var updatedAt:Long,
    var cedula: String,
    var nombre: String,
    var correo: String,
    var estadoCivil: String,
    var password: String,
    var pokemons: ArrayList<PokemonHttp>? = null
) {
    var fechaCreacion: Date
    var fechaActualizacion: Date

    init {
        fechaCreacion = Date(createdAt)
        fechaActualizacion = Date(updatedAt)
    }
}

