package com.llamasoftworks.examen

import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_expansion.*
import java.time.LocalDate
import java.util.*

class ExpansionActivity : AppCompatActivity() {

    var date: LocalDate = LocalDate.now()
    var picker: DatePickerDialog? = null
    var oldName = ""
    var posicion = -1
    var listaCartasOnExp =   mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expansion)
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
            updateExpansion(etEngNameEx.text.toString(), etIdEx.text.toString(),
                date,editText5Ex.text.toString().toDouble(),switch1Ex.isChecked)
            finish()
        }
        fab_deleteEx.setOnClickListener {
            deleteExpansion(oldName)
            finish()
        }
        fab_add_card_to_Exp.setOnClickListener {
            irAddCardToExpActivity()
        }
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // Nombre Layout
            listaCartasOnExp.toList()// Lista
        )
        lv_cards_on_expansion.adapter = adaptador
        lv_cards_on_expansion
            .onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            deleteCard(adaptador,position)}

    }

    override fun onRestart() {
        super.onRestart()
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // Nombre Layout
            listaCartasOnExp.toList()// Lista
        )
        lv_cards_on_expansion.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }


    override fun onStart() {
        super.onStart()
        val numeroEncontrado = intent.getIntExtra("numero", -1)
        if (numeroEncontrado != -1){
            val datos = Companion.readExpansion(numeroEncontrado)
            oldName = datos[0].toString()
            posicion = numeroEncontrado
            loadExpansionData(datos)
            btn_guardar_cartaEx.setVisibility(View.GONE);
        }else{
            fab_deleteEx.hide()
            fab_add_card_to_Exp.hide()
            btn_save_changesEx.setVisibility(View.GONE);
        }
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // Nombre Layout
            listaCartasOnExp.toList()// Lista
        )
        lv_cards_on_expansion.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }

    fun deleteCard(adaptador: ArrayAdapter<String>, index:Int){
        listaCartasOnExp.removeAt(index)
        adaptador.notifyDataSetChanged()
        recreate()
    }

    override fun onActivityResult(requestCode: Int, //NUmero que enviamos
                                  resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode){
            Activity.RESULT_OK->{
                when(requestCode){
                    304 ->{
                        if(data!=null){
                            val posisiondos = data.getIntExtra("indice", -1)
                            listaCartasOnExp.add(Companion.cartas.keys.toList()[posisiondos])
                        }
                    }
                }
            }
            Activity.RESULT_CANCELED->{

            }
        }
    }

    fun guardarExpansion(nombre:String, id:String, releaseDate: LocalDate,precio:Double,tcg:Boolean){
        val nuevaExpansion = Expansion(nombre, id, releaseDate, precio, tcg)
        Companion.anadirExpansion(nuevaExpansion)
    }

    fun loadExpansionData(datos:List<*>){
        etEngNameEx.setText((datos[0].toString()))
        etIdEx.setText((datos[1].toString()))
        editTextDate.setText(datos[2].toString())
        editText5Ex.setText((datos[3].toString()))
        switch1Ex.isChecked = datos[4] as Boolean
        listaCartasOnExp = datos[5] as MutableList<String>
    }

    fun updateExpansion(nombre:String, id:String, releaseDate: LocalDate,precio:Double,tcg:Boolean){
        Companion.updateExpansion(oldName,nombre,id,date,tcg,precio,listaCartasOnExp)
    }

    fun deleteExpansion(nombre: String){
        Companion.deleteExpansion(nombre)
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

}


