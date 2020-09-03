package com.llamasoftworks.examen

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result


class HttpData {

    companion object{
        var cartasList = mutableListOf<String>()
    }
    fun readCard(posicion:Int):List<*>{
        val nombre = cartasList[posicion]
        val url = urlPrincipal + "/carta?nombre="+nombre
        var listaDeDatosCarta = mutableListOf("","",0,true,0.01)
        url
            .httpGet()
            .responseString(){
                    request, response, result ->
                when(result){
                    is Result.Success -> {
                        val data = result.get()
                        val carta = Klaxon().parseArray<Carta>(data)
                        if (carta != null){
                            Log.i("http-klaxon","Datos: ${carta[0].nombre}")
                            listaDeDatosCarta = mutableListOf(carta[0].nombre,carta[0].id,carta[0].level,carta[0].tcg,carta[0].precio)
                        }
                    }
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-klaxon","Error: ${ex.cause}")
                    }
                }
            }
        return listaDeDatosCarta
    }

    var urlPrincipal = "http://192.168.1.3:1337"

    fun createCard(carta:Carta){
        val url = urlPrincipal + "/carta"
        val parametrosCarta = listOf(
            "nombre" to carta.nombre,
            "id" to carta.id,
            "nivel" to carta.level,
            "tcg" to carta.tcg,
            "precio" to carta.precio
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

    fun readCardsNames(){
        val (request, response, result) = "http://192.168.1.3:1337/carta"
            .httpGet()
            .responseString()
        when(result){
            is Result.Success -> {
                val data = result.get()
                val cartas = Klaxon().parseArray<Carta>(data)
                if (cartas != null){
                    HttpData.cartasList.clear()
                    cartas.forEach{
                        HttpData.cartasList.add(it.nombre)
                    }
                    Log.i("http-klaxon","Error: ${HttpData.cartasList}")
                }
            }
            is Result.Failure -> {
                val ex = result.getException()
                Log.i("http-klaxon","Error: ${ex.cause}")
            }
        }

    }
}