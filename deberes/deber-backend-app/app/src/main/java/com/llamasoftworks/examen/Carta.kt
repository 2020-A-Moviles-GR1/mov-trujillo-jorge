package com.llamasoftworks.examen

class Carta (var nombre: String,
             var id: String,
             var level: Int,
             var tcg: Boolean,
             var precio:Double,
             var url:String,
             var image_url:String,
             var lat:Double,
             var long:Double
){

    override fun toString(): String {
        return "Nombre: "+ nombre + "\nID: "+id;
    }
}