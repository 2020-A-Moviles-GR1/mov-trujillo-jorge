package clases

import java.io.Serializable
import java.time.LocalDate

class Expansion(nombre:String, id:String, releaseDate:LocalDate, precio:Double,
                tcg:Boolean):Serializable {
    var nombre:String = nombre
    var id:String = id
    var releaseDate:LocalDate = releaseDate
    var precio:Double = precio
    var tcg:Boolean = tcg
    var cartas:MutableList<String> = mutableListOf()

    fun addCard(nombreCarta:String):Int{
        if (cartas.contains(nombreCarta)){
            return -1
        }else{
            cartas.add(nombreCarta);
            return 0;
        }
    }

}