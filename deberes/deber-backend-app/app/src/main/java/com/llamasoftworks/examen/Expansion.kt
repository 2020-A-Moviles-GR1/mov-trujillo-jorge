package com.llamasoftworks.examen

import java.time.LocalDate

class Expansion@JvmOverloads constructor (
    var nombre:String,
    var id:String,
    @KlaxonDate var releaseDate:LocalDate,
    var precio:Double,
    var tcg:Boolean, var cartas:MutableList<Carta> = mutableListOf()){
    override fun toString(): String {
        return "Nombre: "+ nombre + "\nID: "+id;
    }
}
@Target(AnnotationTarget.FIELD)
annotation class KlaxonDate
