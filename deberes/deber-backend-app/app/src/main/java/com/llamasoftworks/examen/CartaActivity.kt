package com.llamasoftworks.examen

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_carta.*
import java.lang.ref.WeakReference

class CartaActivity : AppCompatActivity() {

    companion object{
        var oldId = ""
    }
    val httpData:HttpData = HttpData()
    val position = -1
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
            httpData.updateCard(etEngName.text.toString(), etId.text.toString(),
                spin.getItemAtPosition(spin.getSelectedItemPosition()).toString().toInt() ,
                switch1.isChecked,editText5.text.toString().toDouble())
            finish()
        }

    }

    override fun onStart() {
        super.onStart()
        val numeroEncontrado = intent.getIntExtra("numero", -1)

        if (numeroEncontrado != -1){
            MyTask(this,numeroEncontrado).execute()
            btn_guardar_carta.setVisibility(View.GONE);
            etId.keyListener = null
        }else{
            fab_delete.hide()
            btn_save_changes.setVisibility(View.GONE);
        }
        fab_delete.setOnClickListener {
            deleteCard(oldId)
            finish()
        }
    }

    fun guardarCarta(nombre:String, id:String, level:Int,tcg:Boolean,precio:Double){
        val nuevaCarta = Carta(nombre,id,level,tcg,precio)
        httpData.createCard(nuevaCarta)
    }

    fun deleteCard(nombre: String){
        httpData.deleteCard(nombre)
    }

    private class MyTask(context: CartaActivity?,position: Int) : AsyncTask<Void, Void?, List<*>>() {
        val activityReference: WeakReference<CartaActivity?> = WeakReference(context)
        val posicion = position
        val etEngName = activityReference.get()?.findViewById<EditText>(R.id.etEngName)
        val etId = activityReference.get()?.findViewById<EditText>(R.id.etId)
        val spinLevel = activityReference.get()?.findViewById<Spinner>(R.id.spin)
        val switch1 = activityReference.get()?.findViewById<Switch>(R.id.switch1)
        val editText5 = activityReference.get()?.findViewById<EditText>(R.id.editText5)
        override fun doInBackground(vararg p0:Void): List<*>{
            val httpData = HttpData()
            return httpData.readCard(posicion)
        }

        override fun onPostExecute(aVoid: List<*>) {
            etEngName!!.setText(aVoid[0].toString())
            etId!!.setText(aVoid[1].toString())
            spinLevel!!.setSelection(aVoid[2] as Int)
            switch1!!.isChecked = aVoid[3] as Boolean
            editText5!!.setText(aVoid[4].toString())
            oldId = aVoid[1].toString()


        }
    }

}
