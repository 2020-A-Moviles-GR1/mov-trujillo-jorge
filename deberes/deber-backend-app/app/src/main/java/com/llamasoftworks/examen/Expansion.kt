package com.llamasoftworks.examen

import java.time.LocalDate
import java.util.*

class Expansion@JvmOverloads constructor (
    var nombre:String,
    var id:String,
    @KlaxonDate var releaseDate:LocalDate,
    var precio:Double,
    var tcg:Boolean, var cartas:MutableList<Carta> = mutableListOf()){



}
@Target(AnnotationTarget.FIELD)
annotation class KlaxonDate
