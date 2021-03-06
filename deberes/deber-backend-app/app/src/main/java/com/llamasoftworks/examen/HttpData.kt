package com.llamasoftworks.examen

import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

class HttpData {
    companion object{
        var cartasList = arrayListOf<Carta>()
    }
    var urlPrincipal = "http://192.168.1.3:1337"
    fun readCard(posicion:Int):List<*>{
        val nombre = cartasList.get(posicion).nombre
        var listaDeDatosCarta = mutableListOf("","",0,true,0.01)
        val url = urlPrincipal + "/carta?nombre="+nombre
        val (request, response, result) = url
            .httpGet()
            .responseString()
        when(result){
            is Result.Success -> {
                val data = result.get()
                val carta = Klaxon().parseArray<Carta>(data)
                if (carta != null){
                    listaDeDatosCarta = mutableListOf(carta[0].nombre,carta[0].id,
                        carta[0].level,carta[0].tcg,carta[0].precio, carta[0].url,
                        carta[0].image_url,carta[0].lat,carta[0].long)
                }
            }
            is Result.Failure -> {
                val ex = result.getException()
            }
        }
        return listaDeDatosCarta
    }

    fun createCard(carta:Carta){
        val url = urlPrincipal + "/carta"
        val parametrosCarta = listOf(
            "nombre" to carta.nombre,
            "id" to carta.id,
            "level" to carta.level,
            "tcg" to carta.tcg,
            "precio" to carta.precio,
            "url" to carta.url,
            "image_url" to carta.image_url,
            "lat" to carta.lat,
            "long" to carta.long
        )
        url.httpPost(parametrosCarta)
            .responseString{
                    req, res, result ->
                when(result){
                    is Result.Failure ->{
                        val error = result.getException()
                    }
                    is Result.Success -> {
                        val usuarioString = result.get()
                    }
                }
            }
    }

    fun readCardsNames():ArrayList<Carta>{
        var list = arrayListOf<Carta>()
        val (request, response, result) = "http://192.168.1.3:1337/carta"
            .httpGet()
            .responseString()
        when(result){
            is Result.Success -> {
                val data = result.get()
                val cartas = Klaxon().parseArray<Carta>(data)
                if (cartas != null){
                    cartasList.clear()
                    cartas.forEach{
                        cartasList.add(it)
                        list.add(it)
                    }
                }
            }
            is Result.Failure -> {
                val ex = result.getException()
                Log.i("http-klaxon","Error: ${ex.cause}")
            }
        }
        return list
    }

    fun deleteCard (idCarta:String){
        val url = urlPrincipal + "/carta/"+idCarta
        url.httpDelete()
            .responseString{
                    req, res, result ->
                when(result){
                    is Result.Failure ->{
                        val error = result.getException()
                    }
                    is Result.Success -> {
                        val usuarioString = result.get()
                    }
                }
            }
    }

    fun updateCard(newName:String, oldId:String,level:Int, tcg:Boolean, precio:Double){
        val url = urlPrincipal + "/carta/"+oldId
        val parametrosCarta = listOf(
            "nombre" to newName,
            "id" to oldId,
            "level" to level,
            "tcg" to tcg,
            "precio" to precio
        )
        url.httpPut(parametrosCarta)
            .responseString{
                    req, res, result ->
                when(result){
                    is Result.Failure ->{
                        val error = result.getException()
                    }
                    is Result.Success -> {
                        val usuarioString = result.get()
                    }
                }
            }
    }
}