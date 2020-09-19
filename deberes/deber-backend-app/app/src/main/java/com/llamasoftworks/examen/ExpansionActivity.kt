package com.llamasoftworks.examen

import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_expansion.*
import java.lang.ref.WeakReference
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList
import kotlin.reflect.typeOf

class ExpansionActivity : AppCompatActivity() {


    var picker: DatePickerDialog? = null
    val httpDataExp = HttpDataExp()
    var posicion = -1


    companion object{
        var date: LocalDate = LocalDate.now()
        var oldId = ""
        var posisiondos = -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expansion)
        Log.i("http-klaxon","On create expb axc")
        btn_release_date.setOnClickListener(View.OnClickListener {
            datePicker()
        })
        btn_guardar_cartaEx.setOnClickListener {
            guardarExpansion(
                etEngNameEx.text.toString(), etIdEx.text.toString(),
                date, editText5Ex.text.toString().toDouble(), switch1Ex.isChecked
            )
            finish()
        }
        btn_save_changesEx.setOnClickListener {
            HttpDataExp().updateExpansion(etEngNameEx.text.toString(), etIdEx.text.toString(),
                date,switch1Ex.isChecked,editText5Ex.text.toString().toDouble(), HttpDataExp.idsCartasOnExp as  ArrayList<String>)
            posisiondos = -1
            finish()
        }
        fab_deleteEx.setOnClickListener {
            deleteExpansion(oldId)
            finish()
        }
        fab_add_card_to_Exp.setOnClickListener {
            irAddCardToExpActivity()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("http-klaxon","On start exp act ${HttpDataExp.idsCartasOnExp} ")
        val numeroEncontrado = intent.getIntExtra("numero", -1)
        posicion = numeroEncontrado
        if (numeroEncontrado != -1){
            MyTask(this,numeroEncontrado).execute()
            btn_guardar_cartaEx.setVisibility(View.GONE);
            etIdEx.keyListener = null
            lv_cards_on_expansion
                .onItemClickListener = AdapterView.OnItemClickListener {
                    parent, view, position, id ->
                deleteCard(lv_cards_on_expansion.adapter as ArrayAdapter<String>,position)}
        }else{
            fab_deleteEx.hide()
            fab_add_card_to_Exp.hide()
            btn_save_changesEx.setVisibility(View.GONE);
        }

        Log.i("http-klaxon","On start exp act 2 ${HttpDataExp.idsCartasOnExp} ")
    }

    fun deleteCard(adaptador: ArrayAdapter<String>, index:Int){
        Log.i("http-klaxon","IDS CARTAS: ${ HttpDataExp.idsCartasOnExp} e INDEX ${index}")
            HttpDataExp.idsCartasOnExp.removeAt(index)
            HttpDataExp.listaCartasOnExp.removeAt(index)
        Log.i("http-klaxon","IDS CARTAS @: ${ HttpDataExp.idsCartasOnExp}")
        adaptador.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode){
            Activity.RESULT_OK->{
                when(requestCode){
                    304 ->{
                        if(data!=null){
                            posisiondos = data.getIntExtra("indice", -1)
                            HttpDataExp.listaCartasOnExp.add(HttpData.cartasList[posisiondos].nombre)
                            //Log.i("http-klaxon","On act result ${HttpDataExp.idsCartasOnExp}  POSICION 2 = ${posisiondos}")
                        }
                    }
                }
            }
            Activity.RESULT_CANCELED->{

            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        HttpDataExp.listaCartasOnExp.clear()
        HttpDataExp.idsCartasOnExp.clear()
        posisiondos = -1
        posicion = -1
        oldId = ""
    }

    fun guardarExpansion(nombre:String, id:String, releaseDate: LocalDate,precio:Double,tcg:Boolean){
        val nuevaExpansion = Expansion(nombre, id, releaseDate, precio, tcg)
        val httpDataExp = HttpDataExp()
        httpDataExp.createExpansion(nuevaExpansion)
    }

    fun deleteExpansion(id: String){
        httpDataExp.deleteExpansion(id)
    }

    fun datePicker (){
        val cldr = Calendar.getInstance()
        val day = cldr[Calendar.DAY_OF_MONTH]
        val month = cldr[Calendar.MONTH]
        val year = cldr[Calendar.YEAR]
        picker = DatePickerDialog(
            this,
            OnDateSetListener { view, year, monthOfYear, dayOfMonth -> date = LocalDate.of(year,monthOfYear+1,dayOfMonth);
                editTextDate.setText(date.toString()) },
            year,
            month,
            day
        )
        picker!!.updateDate(date.year,date.monthValue-1, date.dayOfMonth)
        picker!!.show()
    }

    fun irAddCardToExpActivity(){
        val intentExplicito = Intent(
            this,
            AddCardToExpActivity::class.java
        )
        intentExplicito.putExtra("posicion", posicion)
        startActivityForResult(intentExplicito,304)
    }

    private class MyTask(val context: Activity?,position: Int) : AsyncTask<Void, Void?, List<*>>() {
        val activityReference: WeakReference<Activity?> = WeakReference(context)
        val posicion = position
        var liV = activityReference.get()?.findViewById<ListView>(R.id.lv_cards_on_expansion)
        val etEngNameEx = activityReference.get()?.findViewById<EditText>(R.id.etEngNameEx)
        val etIdEx = activityReference.get()?.findViewById<EditText>(R.id.etIdEx)
        val etDateEx = activityReference.get()?.findViewById<EditText>(R.id.editTextDate)
        val switch1Ex = activityReference.get()?.findViewById<Switch>(R.id.switch1Ex)
        val editText5Ex = activityReference.get()?.findViewById<EditText>(R.id.editText5Ex)

        override fun doInBackground(vararg p0:Void): List<*>{
            val datos = HttpDataExp().readExpansion(posicion)
            Log.i("http-klaxon","On baccground ${HttpDataExp.idsCartasOnExp} ==---${HttpDataExp.expansionesList}")
            (datos[5] as ArrayList<Carta>).forEach {
                if (!HttpDataExp.listaCartasOnExp.contains(it.nombre)){
                    HttpDataExp.listaCartasOnExp.add(it.nombre)

                }
                if(!HttpDataExp.idsCartasOnExp.contains(it.id)){
                    HttpDataExp.idsCartasOnExp.add(it.id)
                }
                Log.i("http-klaxon","On background 2 ${HttpDataExp.idsCartasOnExp} ==---${HttpDataExp.expansionesList} ")
            }
            var num = ""
            Log.i("http-klaxon","On back posisiondos ${posisiondos} ")
            if(posisiondos != -1){
                num = HttpData().readCard(posisiondos)[1] as String
                Log.i("http-klaxon","On back  ${posisiondos} ")
            }
            return listOf(datos,num)
        }

        override fun onPostExecute(respuesta: List<*>) {
            val ad =ArrayAdapter(
                context,android.R.layout.simple_list_item_1, HttpDataExp.listaCartasOnExp)
             liV?.adapter  = ad
            (liV?.adapter as ArrayAdapter<String>).notifyDataSetChanged()
            val aVoid = respuesta.get(0) as MutableList<*>
            date = aVoid[2] as LocalDate
            etEngNameEx!!.setText(aVoid[0].toString())
            etIdEx!!.setText(aVoid[1].toString())
            etDateEx!!.setText(aVoid[2].toString())
            switch1Ex!!.isChecked = aVoid[4] as Boolean
            editText5Ex!!.setText(aVoid[3].toString())
            oldId = aVoid[1].toString()
            if (respuesta.get(1)!=""&& !HttpDataExp.idsCartasOnExp.contains(respuesta.get(0))){
                HttpDataExp.idsCartasOnExp.add(respuesta.get(1) as String)
            }

        }
    }
}

