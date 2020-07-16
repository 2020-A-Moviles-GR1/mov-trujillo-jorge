package com.llamasoftworks.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val listaEntrenadores = arrayListOf<Entrenador>()
        listaEntrenadores.add(Entrenador("Jorge", "Trujillo"))
        listaEntrenadores.add(Entrenador("Dony", "Cates"))
        listaEntrenadores.add(Entrenador("Man", "Nose"))
        listaEntrenadores.add(Entrenador("Jim", "Worm"))

        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // Nombre Layout
            listaEntrenadores // Lista
        )

        lv_numeros.adapter = adaptador

        lv_numeros
            .onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            Log.i("list-view", "Posicion $position")}

        btn_anadir_entrenador.setOnClickListener { btn -> anadirEntrenador(adaptador,listaEntrenadores) }


    }

    fun anadirEntrenador(adaptador: ArrayAdapter<Entrenador>, listaEntrenadores: ArrayList<Entrenador>){
        listaEntrenadores.add(Entrenador("Eo", "Lol"))
        adaptador.notifyDataSetChanged()

    }
}

