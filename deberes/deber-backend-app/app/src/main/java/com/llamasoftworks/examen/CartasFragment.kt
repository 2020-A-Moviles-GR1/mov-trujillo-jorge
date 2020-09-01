package com.llamasoftworks.examen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_cartas.*

class CartasFragment : Fragment() {

    val httpData:HttpData = HttpData()
    var cardNum = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_cartas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab_add_card.setOnClickListener{
            irCartaActivity()
        }
    }

    override fun onStart() {
        super.onStart()
        httpData.readCardsNames()
        val adaptador = ArrayAdapter(
            activity, // Contexto
            android.R.layout.simple_list_item_1, // Nombre Layout
            httpData.cartasList// Lista
        )
        lv_cartas.adapter = adaptador
        adaptador.notifyDataSetChanged()
        lv_cartas
            .onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            cardNum = position
            irCartaActivityEdit()}
    }

    fun irCartaActivity(){
        val intentExplicito = Intent(
            activity,
            CartaActivity::class.java
        )
        startActivity(intentExplicito)
    }

    fun irCartaActivityEdit(){
        val intentExplicito = Intent(
            activity,
            CartaActivity::class.java
        )
        intentExplicito.putExtra("numero", cardNum)
        startActivity(intentExplicito)
    }
}
