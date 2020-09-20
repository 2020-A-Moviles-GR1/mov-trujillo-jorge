package com.llamasoftworks.moviles

import com.beust.klaxon.Converter
import java.util.*

class PokemonHttp@JvmOverloads constructor (
    var id:Int,
    var createdAt: Long,
    var updatedAt: Long,
    var nombre: String,
    @KlxnUserHttp var usuario: Any
) {
    var fechaCreacion: Date
    var fechaActualizacion: Date

    init {
        fechaCreacion = Date(createdAt)
        fechaActualizacion = Date(updatedAt)
    }
}
@Target(AnnotationTarget.FIELD)
annotation class KlxnUserHttp



