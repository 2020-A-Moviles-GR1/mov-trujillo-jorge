package com.llamasoftworks.examen

import android.os.AsyncTask
import android.util.Log
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import org.json.JSONArray
import org.json.JSONObject
import java.net.URLEncoder


class YuGiAPI {

    var urlPrincipal = "https://db.ygoprodeck.com/api/v7/cardinfo.php?name="
    fun readCard(nombre:String):ArrayList<Any>{
        val urlYugiDb = "https://db.ygoprodeck.com/card/?search="
        var listaDeDatosCarta = arrayListOf<Any>()
        val url = urlPrincipal +nombre
        val (request, response, result) = url
            .httpGet()
            .responseString()
        when(result){
            is Result.Success -> {
                val data = result.get()
                val reader = JSONObject(data)
                val cardData = (reader.get("data") as JSONArray).get(0) as JSONObject
                listaDeDatosCarta.add(cardData.get("id"))
                if (cardData.has("level")) listaDeDatosCarta.add(cardData.getInt("level")) else listaDeDatosCarta.add(0)
                listaDeDatosCarta.add(((cardData.get("card_prices") as JSONArray).get(0) as JSONObject).get("amazon_price"))
                listaDeDatosCarta.add(urlYugiDb+URLEncoder.encode(nombre))
                listaDeDatosCarta.add(((cardData.get("card_images") as JSONArray).get(0) as JSONObject).get("image_url_small"))
                Log.i("aaa","${listaDeDatosCarta}")
            }
            is Result.Failure -> {
                val ex = result.getException()
            }
        }
        return listaDeDatosCarta
    }

}
