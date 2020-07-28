package com.llamasoftworks.examen

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_add_card_to_exp.*

class AddCardToExpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card_to_exp)
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // Nombre Layout
            Companion.cartas.keys.toList()// Lista
        )
        lv_cards_to_add.adapter = adaptador
        adaptador.notifyDataSetChanged()
        lv_cards_to_add.onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            val intentRespuesta = Intent()
            intentRespuesta.putExtra("indice", position)
            setResult(
                RESULT_OK,
                intentRespuesta
            )
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        val numeroEncontrado = intent.getIntExtra("posicion",-1)
        val datos = Companion.readExpansion(numeroEncontrado)
    }


}
