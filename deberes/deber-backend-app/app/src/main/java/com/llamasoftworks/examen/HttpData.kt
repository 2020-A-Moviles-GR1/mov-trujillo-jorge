package com.llamasoftworks.examen

import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

class HttpData {
    var urlPrincipal = "http://192.168.1.3:1337"
    var cartasList = mutableListOf<String>()
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
                        Log.i("http-klaxon","Error: ${error}")
                    }
                    is Result.Success -> {
                        val usuarioString = result.get()
                        Log.i("http-klaxon","${usuarioString}")
                    }
                }
            }
    }

    fun readCardsNames(){
        val url = urlPrincipal + "/carta"
        url.httpGet()
            .responseString{
                    request, response, result ->
                when(result){
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("http-klaxon","Data: ${data}")
                        val cartas = Klaxon().parseArray<Carta>(data)
                        Log.i("http-klaxon","Data: ${cartas}")
                        if (cartas != null){
                            cartas.forEach{
                                cartasList.add(it.nombre)
                            }
                        }
                    }
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-klaxon","Error: ${ex.cause}")
                    }
                }
            }
    }
}