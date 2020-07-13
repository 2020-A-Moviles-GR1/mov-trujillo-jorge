package com.llamasoftworks.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val listaEntrenadores = arrayListOf<Entrenador>()
        listaEntrenadores.add(Entrenador("Jorge", "Trujillo"))
        listaEntrenadores.add(Entrenador("Dony", "Cates"))
        listaEntrenadores.add(Entrenador("Man", "Nose"))
        listaEntrenadores.add(Entrenador("Jim", "Worm"))
    }
}