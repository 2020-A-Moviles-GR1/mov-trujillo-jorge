package com.llamasoftworks.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        val listaUsuarios = arrayListOf<UsuarioHttp>()
        listaUsuarios.add(
            UsuarioHttp(
                1,
                123123123,
                123123123,
                "8767543468",
                "Jor",
                "jj@aa.com",

                "Soltero",
                "123456",
                arrayListOf<PokemonHttp>()
            )
        )
        listaUsuarios.add(
            UsuarioHttp(
                2,
                123123123,
                123123123,
                "0987654433",
                "Al",
                "jj@aa.com",

                "Soltero",
                "123456",
                arrayListOf<PokemonHttp>()
            )
        )
        listaUsuarios.add(
            UsuarioHttp(
                3,
                123123123,
                123123123,
                "0987653457",
                "JorAl",
                "jj@aa.com",

                "Soltero",
                "123456",
                arrayListOf<PokemonHttp>()
            )
        )
        iniciarRecyclerView(
            listaUsuarios,this,rv_uduarios
        )
    }

    fun iniciarRecyclerView(
        list: List<UsuarioHttp>,
        activity: RecyclerViewActivity,
        recyclerView: RecyclerView
    ){
        val adaptadorUsuario = RecyclerAdaptador(
            list,
            activity,
            recyclerView
        )
        rv_uduarios.adapter = adaptadorUsuario
        rv_uduarios.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rv_uduarios.layoutManager = LinearLayoutManager(activity)
        adaptadorUsuario.notifyDataSetChanged()
    }
}
