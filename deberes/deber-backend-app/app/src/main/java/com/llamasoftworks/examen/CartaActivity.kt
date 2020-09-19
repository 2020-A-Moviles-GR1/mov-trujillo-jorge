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
                switch1.isChecked,editText5.text.toString().toDouble(),etURL.text.toString(),
                etURLImagen.text.toString(),etLat.text.toString().toDouble(),etLong.text.toString().toDouble())
            finish()
        }
        btn_save_changes.setOnClickListener {
            httpData.updateCard(etEngName.text.toString(), etId.text.toString(),
                spin.getItemAtPosition(spin.getSelectedItemPosition()).toString().toInt() ,
                switch1.isChecked,editText5.text.toString().toDouble())
            finish()
        }
        button.setOnClickListener {
            getDataYugiApi(etEngName.text.toString())
        }
        etEngName.setAdapter(ArrayAdapter(this,
            android.R.layout.simple_dropdown_item_1line,MainActivity.responseList))
    }

    override fun onStart() {
        super.onStart()
        val numeroEncontrado = intent.getIntExtra("numero", -1)
        if (numeroEncontrado != -1){
            tarea(numeroEncontrado)
            btn_guardar_carta.setVisibility(View.GONE);
            button.setVisibility(View.GONE)
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

    fun guardarCarta(nombre:String, id:String, level:Int,tcg:Boolean,precio:Double,url:String,image_url:String,lat:Double,long:Double){
        val nuevaCarta = Carta(nombre,id,level,tcg,precio,url,image_url,lat, long)
        httpData.createCard(nuevaCarta)
    }

    fun deleteCard(nombre: String){
        httpData.deleteCard(nombre)
    }

    fun getDataYugiApi(nombre: String) {
        class httpGetAsycTask (): AsyncTask<Void, Void, ArrayList<Any>>() {
            override fun doInBackground(vararg p0: Void?): ArrayList<Any> {
                return YuGiAPI().readCard(nombre)
            }
            override fun onPostExecute(result: ArrayList<Any>?) {
                super.onPostExecute(result)
                etId.setText(result?.get(0).toString())
                spin.setSelection(result?.get(1) as Int)
                editText5.setText(result?.get(2).toString())
                etURL.setText(result?.get(3).toString())
                etURLImagen.setText(result?.get(4).toString())
            }
        }
        httpGetAsycTask().execute()
    }

    fun tarea(position: Int){
        class MyTask() : AsyncTask<Void, Void?, List<*>>() {
            override fun doInBackground(vararg p0:Void): List<*>{
                val httpData = HttpData()
                return httpData.readCard(position)
            }

            override fun onPostExecute(aVoid: List<*>) {
                etEngName!!.setText(aVoid[0].toString())
                etId!!.setText(aVoid[1].toString())
                spin!!.setSelection(aVoid[2] as Int)
                switch1!!.isChecked = aVoid[3] as Boolean
                editText5!!.setText(aVoid[4].toString())
                etURL!!.setText(aVoid[5].toString())
                etURLImagen!!.setText(aVoid[6].toString())
                etLat!!.setText(aVoid[7].toString())
                etLong!!.setText(aVoid[8].toString())
                oldId = aVoid[1].toString()
            }
        }
        MyTask().execute()
    }
}
