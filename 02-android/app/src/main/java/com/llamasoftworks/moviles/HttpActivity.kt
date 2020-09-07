package com.llamasoftworks.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.*
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
            //Log.i("http-klaxon","Nombe: ${pokemonInstancia.nombre} \n Fecha: ${pokemonInstancia.fechaCreacion}")
        }

        val url = urlPrincipal + "/pokemon"
        url.httpGet()
            .responseString{
                request, response, result ->
                when(result){
                    is Result.Success -> {
                        val data = result.get()
                        //Log.i("http-klaxon","Data: ${data}")
                        val pokemons = Klaxon()
                            .fieldConverter(KlxnUserHttp::class, toUserConverter)
                            .parseArray<PokemonHttp>(data)
                        //Log.i("http-klaxon","Nombreeee: ${pokemons?.get(0)?.nombre} \n")
                        if (pokemons != null){
                            pokemons.forEach{
                                //Log.i("http-klaxon","Nombreeee: ${it.nombre} \n ${it.id}")
                                if(it.usuario!=null){
                                    //it.pokemons.forEach {
                                        Log.i("http-klaxon","\n ID: ${it.id} \n" +
                                                " Nombre ${it.nombre}\n Nombre Usuario: ${(it.usuario as UsuarioHttp).nombre}")
                                    //}
                                }
                            }
                        }
                    }
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-klaxon","Error lolo: ${ex}")
                    }
                }
            }
    }

    val toUserConverter = object: Converter {
        override fun canConvert(cls: Class<*>)
                = cls == UsuarioHttp::class.java

        override fun fromJson(jv: JsonValue): Any? =
            if (jv.int is Int) {
                jv.int
            } else if(jv.obj?.get("nombre") is String){
                Klaxon().parseFromJsonObject<UsuarioHttp>(jv.obj!!)
            } else {
                throw KlaxonException("Couldn't parse date: ${jv.obj?.values}")
            }

        override fun toJson(o: Any)
                = """ { "usuario" : $o} """.trimMargin()
    }
}
