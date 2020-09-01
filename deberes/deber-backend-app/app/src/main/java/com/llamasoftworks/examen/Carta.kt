package com.llamasoftworks.examen

class Carta (nombre: String, id: String, level: Int?, tcg: Boolean, precio:Double){

    var nombre: String = nombre
    var id: String = id
    var level: Int? = level
    var precio: Double = precio
    var tcg: Boolean = tcg

    constructor (nombre: String,id: String,level: Int?,precio: Double,tcg: Boolean):this(nombre,id,level,tcg,precio)

    override fun toString(): String {
        return "Nombre: "+ nombre + "\nID: "+id+ "\nLevel: "+level+ "\nTCG: "+tcg+ "\nprecio: "+precio;
    }
}