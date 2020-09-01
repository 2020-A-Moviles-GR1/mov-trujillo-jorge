package com.llamasoftworks.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

class HttpData {
    var urlPrincipal = "http://192.168.1.3:1337"

    fun createCard(carta:Carta){
        val url = urlPrincipal + "/carta"
        val parametrosCarta = listOf(
            "nombre" to carta.nombre,
            "id" to carta.id,
            "nivel" to carta.level,
            "precio" to carta.precio,
            "tcg" to carta.tcg
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
}