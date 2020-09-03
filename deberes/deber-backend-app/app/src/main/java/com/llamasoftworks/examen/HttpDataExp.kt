package com.llamasoftworks.examen

import android.util.Log
import com.beust.klaxon.Converter
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon
import com.beust.klaxon.KlaxonException
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalQueries.localDate




class HttpDataExp {
    companion object{
        var expansionesList = mutableListOf<String>()
    }
    var urlPrincipal = "http://192.168.1.3:1337"
    val dateConverter = object: Converter {
        override fun canConvert(cls: Class<*>)
                = cls == LocalDate::class.java

        override fun fromJson(jv: JsonValue) =
            if (jv.longValue != null) {
                Instant.ofEpochMilli(jv.longValue!!).atZone(ZoneId.systemDefault()).toLocalDate();
            } else {
                throw KlaxonException("Couldn't parse date: ${jv.string}")
            }

        override fun toJson(o: Any)
                = """ { "releaseDate" : $o } """
    }

    fun readExpansionNames(){

        val (request, response, result) = "http://192.168.1.3:1337/expansion"
            .httpGet()
            .responseString()
        when(result){
            is Result.Success -> {
                val data = result.get()
                val cartas = Klaxon()
                    .fieldConverter(KlaxonDate::class, dateConverter)
                    .parseArray<Expansion>(data)
                if (cartas != null){
                    HttpData.expansionesList.clear()
                    cartas.forEach{
                        HttpData.expansionesList.add(it.nombre)
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

    fun createExpansion(expansion: Expansion){
        val url = urlPrincipal + "/expansion"
        val instant: Instant = expansion.releaseDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
        val parametrosCarta = listOf(
            "nombre" to expansion.nombre,
            "id" to expansion.id,
            "releaseDate" to instant.toEpochMilli(),
            "precio" to expansion.precio,
            "tcg" to expansion.tcg
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
}