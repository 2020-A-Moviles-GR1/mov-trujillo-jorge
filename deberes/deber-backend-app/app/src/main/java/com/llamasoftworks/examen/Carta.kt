package com.llamasoftworks.examen

class Carta (var nombre: String, var id: String, var level: Int, var tcg: Boolean, var precio:Double){

    override fun toString(): String {
        return "Nombre: "+ nombre + "\nID: "+id+ "\nLevel: "+level+ "\nTCG: "+tcg+ "\nprecio: "+precio;
    }
}