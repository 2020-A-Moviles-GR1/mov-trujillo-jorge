package com.llamasoftworks.examen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.AttributeSet
import android.view.View
import android.widget.Adapter
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_carta.*


class CartaActivity : AppCompatActivity() {

    var oldName = ""
    val attributos = arrayOf(
        "0","1","2","3","4","5","6","7","8","9","10","11","12"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_carta)
        val arrayAdapter = ArrayAdapter<Any?>(
            this.getApplicationContext(),
            android.R.layout.simple_list_item_1, attributos
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = arrayAdapter
        btn_guardar_carta.setOnClickListener {
            guardarCarta(etEngName.text.toString(), etId.text.toString(),
                spin.getItemAtPosition(spin.getSelectedItemPosition()).toString().toInt() ,
                switch1.isChecked,editText5.text.toString().toDouble())
            finish()
        }
        btn_save_changes.setOnClickListener {
            updateCarta(etEngName.text.toString(), etId.text.toString(),
                spin.getItemAtPosition(spin.getSelectedItemPosition()).toString().toInt() ,
                switch1.isChecked,editText5.text.toString().toDouble())
            finish()
        }
        fab_delete.setOnClickListener {
            deleteCard(oldName)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        val numeroEncontrado = intent.getIntExtra("numero", -1)
        if (numeroEncontrado != -1){
            val datos = Companion.readCard(numeroEncontrado)
            oldName = datos[0].toString()
            loadCardData(datos)
        }else{
            fab_delete.hide()
            btn_save_changes.setVisibility(View.GONE);
        }
    }

    fun guardarCarta(nombre:String, id:String, level:Int,tcg:Boolean,precio:Double){
        val nuevaCarta = Carta(nombre,id,level,tcg,precio)
        Companion.anadirCarta(nuevaCarta)
    }

    fun updateCarta(nombre:String, id:String, level:Int,tcg:Boolean,precio:Double){
        Companion.updateCard(oldName,nombre,id,level,tcg,precio)
    }

    fun deleteCard(nombre: String){
        Companion.deleteCard(nombre)
    }

    fun loadCardData(datos:List<*>){
        System.out.println( datos[3] as Boolean)
        etEngName.setText((datos[0].toString()))
        etId.setText((datos[1].toString()))
        spin.setSelection(datos[2] as Int)
        switch1.isChecked = datos[3] as Boolean
        editText5.setText((datos[4].toString()))
    }

    fun irCartaActivity(){
        val intentExplicito = Intent(
            this,
            MainActivity::class.java
        )
        startActivity(intentExplicito)
    }
}
