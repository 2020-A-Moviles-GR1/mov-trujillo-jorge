package com.llamasoftworks.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_http.*

class HttpActivity : AppCompatActivity() {
    var urlPrincipal = "http://192.168.1.3:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)

        btn_obtener.setOnClickListener {
            obtenerUsuarios()
        }

        btn_crear
            .setOnClickListener {
                crearUsuario()
            }
    }

    fun crearUsuario(){
        val url = urlPrincipal+"/Usuario"
        val parametrosUsuario = listOf(
            "cedula" to "0401864275",
            "nombre" to "Ain",
            "correo" to "ain@example.com",
            "estadoCivil" to "Casado",
            "password" to "SuperSeg22"
        )
        url.httpPost(parametrosUsuario)
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

    fun obtenerUsuarios(){
        val pokemonString = """{
            "createdAt": 1597890405678,
            "updatedAt": 1597890405678,
            "id": 2,
            "nombre": "Lunala",
            "usuario": 3,
            "batalla": 1
          }""".trimIndent()

        val pokemonInstancia = Klaxon()
            .parse<PokemonHttp>(pokemonString)
        if(pokemonInstancia != null){
            Log.i("http-klaxon","Nombe: ${pokemonInstancia.nombre} \n Fecha: ${pokemonInstancia.fechaCreacion}")
        }

        val url = urlPrincipal + "/Usuario"
        url.httpGet()
            .responseString{
                request, response, result ->
                when(result){
                    is Result.Success -> {
                        val data = result.get()
                        //Log.i("http-klaxon","Data: ${data}")
                        val usuarios = Klaxon()
                            .parseArray<UsuarioHttp>(data)
                        if (usuarios != null){
                            usuarios.forEach{
                                Log.i("http-klaxon","Nombre: ${it.nombre} \n ${it.estadoCivil}")
                                if(it.pokemons.size>0){
                                    it.pokemons.forEach {
                                        Log.i("http-klaxon","Nombre Poke: ${it.nombre}")
                                    }
                                }
                            }


                        }
                    }
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-klaxon","Error: ${ex.message}")
                    }
                }
            }
    }
}
