package clases

import java.io.*
import java.time.LocalDate


class Files (){

    var rutaCartas: String = ""
    var rutaExpansiones: String =""
    var cartas: MutableMap<String, Carta> = mutableMapOf()
    var expansiones: MutableMap<String, Expansion> = mutableMapOf()

    fun addCard(name:String,id:String, level:Int, tcg:Boolean, precio:Double):Int{
        if(this.cartas.containsKey(name)){
            return -1
        }else{
            val carta = Carta(name, id,level,tcg,precio)
            this.cartas.put(carta.nombre,carta)
            return 0
        }
    }
    fun addExpansion(name:String,id:String, fecha:LocalDate, precio:Double, tcg:Boolean):Int{
        if(this.expansiones.containsKey(name)){
            return -1
        }else{
            val expansion = Expansion(name, id,fecha,precio, tcg)
            this.expansiones.put(expansion.nombre,expansion)
            return 0
        }
    }

    fun writeFileCards(datos:MutableMap<String,Carta>){
        val file = this.rutaCartas
        ObjectOutputStream(FileOutputStream(file)).use{
            it -> it.writeObject(datos)
        }
    }

    fun writeFileExpansions(datos:MutableMap<String,Expansion>){
        val file = this.rutaExpansiones
        ObjectOutputStream(FileOutputStream(file)).use{
            it -> it.writeObject(datos)
        }
    }

    fun readFile(name:String){
        try {
            ObjectInputStream(FileInputStream(name)).use { it ->
                val datos = it.readObject()
                when (datos) {
                    is MutableMap<*, *> -> cartas = datos as MutableMap<String, Carta>
                    else -> println("Fallo al deserializar")
                }
            }
        }catch (e:EOFException){

        }
    }

    fun readFileExpansiones(name:String){
        try {
            ObjectInputStream(FileInputStream(name)).use { it ->
                val datos = it.readObject()
                when (datos) {
                    is MutableMap<*, *> -> expansiones = datos as MutableMap<String, Expansion>
                    else -> println("Fallo al deserializar")
                }
            }
        }catch (e:EOFException){

        }
    }

    fun getCardsKeys(): List<String>{
        return cartas.keys.toMutableList()
    }

    fun getExpKeys(): List<String>{
        return expansiones.keys.toMutableList()
    }

    fun readCard(name: String): List<*>{
        if(cartas.containsKey(name)){
            val card = cartas.getValue(name)
            return listOf(card.nombre, card.id, card.level, card.tcg, card.precio)
        }else{
            return listOf("")
        }
    }

    fun readExp(name: String): List<*>{
        if(expansiones.containsKey(name)){
            val card = expansiones.getValue(name)
            return listOf(card.nombre, card.id, card.releaseDate, card.tcg, card.precio,card.cartas)
        }else{
            return listOf("")
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

    fun deleteExpansion(name: String):Int{
        if(this.expansiones.containsKey(name)){
            this.expansiones.remove(name)
            return 0
        }else{
            return -1
        }
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

    fun updateExpansion(oldName: String, newName: String, id: String,
                        releaseDate:LocalDate, precio: Double, tcg: Boolean,
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

    fun deleteCardFromAll(nombreCarta: String){
        expansiones.map { it-> if (it.value.cartas.contains(nombreCarta)){
            it.value.cartas.remove(nombreCarta)
            updateExpansion(it.value.nombre,it.key, it.value.id,it.value.releaseDate,
            it.value.precio,it.value.tcg,it.value.cartas)
        } }
    }

    fun updateCardForAll(oldName: String,newName: String){
        expansiones.map { it-> if (it.value.cartas.contains(oldName)){
            it.value.cartas.remove(oldName)
            it.value.cartas.add(newName)
            updateExpansion(it.value.nombre,it.key, it.value.id,it.value.releaseDate,
                    it.value.precio,it.value.tcg,it.value.cartas)
        } }
    }
}
