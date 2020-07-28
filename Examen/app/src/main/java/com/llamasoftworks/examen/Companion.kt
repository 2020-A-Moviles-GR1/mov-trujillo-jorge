package com.llamasoftworks.examen

import java.time.LocalDate

class Companion {
    companion object {
        var cartas: MutableMap<String, Carta> = mutableMapOf()
        var expansiones: MutableMap<String, Expansion> = mutableMapOf("Lol" to Expansion("Lol", "12", LocalDate.now(),12.0,false))



        fun anadirCarta(carta:Carta){
            cartas.put(carta.nombre, carta)
        }

        fun anadirExpansion(expansion:Expansion){
            expansiones.put(expansion.nombre, expansion)
        }

        fun readCard(posicion:Int):List<*>{
            val name = cartas.keys.toList()[posicion]
            val card = cartas.get(name)
            return listOf(card!!.nombre,card!!.id,card!!.level,card!!.tcg,card!!.precio)
        }

        fun readExpansion(posicion:Int):List<*>{
            val name = expansiones.keys.toList()[posicion]
            val exp= expansiones.get(name)
            return listOf(exp!!.nombre,exp!!.id,exp!!.releaseDate,exp!!.precio,exp!!.tcg, exp!!.cartas)
        }

        fun updateCard(oldName:String,newName:String,id:String, level:Int, tcg:Boolean, precio:Double){
            val oldCarta = cartas.get(oldName)
            if (oldCarta != null) {
                if (oldName!=newName){
                    oldCarta.nombre=newName
                    oldCarta.id=id
                    oldCarta.level= level
                    oldCarta.tcg= tcg
                    oldCarta.precio = precio
                    var newCard= oldCarta
                    cartas.remove(oldName)
                    cartas.put(newName,newCard)
                }else{
                    oldCarta.id=id
                    oldCarta.level= level
                    oldCarta.tcg= tcg
                    oldCarta.precio = precio
                }
            }
        }

        fun deleteCard(name: String):Int{
            if(this.cartas.containsKey(name)){
                this.cartas.remove(name)
                return 0
            }else{
                return -1
            }
        }

        fun updateExpansion(oldName: String, newName: String, id: String,
                            releaseDate: LocalDate, tcg: Boolean, precio: Double,
                            listCartas: MutableList<*>){
            val oldExpansion = expansiones.get(oldName)
            if (oldExpansion != null) {
                if (oldName!=newName){
                    oldExpansion.nombre=newName
                    oldExpansion.id=id
                    oldExpansion.releaseDate= releaseDate
                    oldExpansion.tcg= tcg
                    oldExpansion.precio = precio
                    oldExpansion.cartas = listCartas as MutableList<String>
                    val newExpansion= oldExpansion
                    expansiones.remove(oldName)
                    expansiones.put(newName,newExpansion)
                }else{
                    oldExpansion.id=id
                    oldExpansion.releaseDate= releaseDate
                    oldExpansion.tcg= tcg
                    oldExpansion.precio = precio
                    oldExpansion.cartas = listCartas as MutableList<String>
                }
            }
        }

        fun deleteExpansion(name: String):Int{
            if(this.expansiones.containsKey(name)){
                this.expansiones.remove(name)
                return 0
            }else{
                return -1
            }
        }
    }
}