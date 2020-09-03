package com.llamasoftworks.examen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_expansiones.*

class ExpansionesFragment : Fragment() {

    var expNum = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_expansiones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab_add_exp.setOnClickListener {
            this.irExpActivity()
        }
    }

    override fun onStart() {
        super.onStart()
        val adaptador = ArrayAdapter(
            activity, // Contexto
            android.R.layout.simple_list_item_1, // Nombre Layout
            Companion.expansiones.keys.toList()// Lista
        )

        lv_expanciones.adapter = adaptador
        adaptador.notifyDataSetChanged()
        lv_expanciones
            .onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            expNum = position
            irExpansionActivityEdit()}
    }

    fun irExpActivity(){
        val intentExplicito = Intent(
            activity,
            ExpansionActivity::class.java
        )
        startActivity(intentExplicito)
    }

    fun irExpansionActivityEdit(){
        val intentExplicito = Intent(
            activity,
            ExpansionActivity::class.java
        )
        intentExplicito.putExtra("numero", expNum)
        startActivity(intentExplicito)
    }

}
