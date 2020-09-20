package com.llamasoftworks.examen

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_add_card_to_exp.*
import java.lang.ref.WeakReference

class AddCardToExpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card_to_exp)
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // Nombre Layout
            HttpData.cartasList// Lista
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
        MyTask(this).execute()
    }

    private class MyTask(context: Activity?) : AsyncTask<Void, Void?, Void?>() {
        val activityReference: WeakReference<Activity?> = WeakReference(context)
        var liV = activityReference.get()?.findViewById<ListView>(R.id.lv_cards_to_add)
        override fun doInBackground(vararg p0:Void): Void? {
            val httpData = HttpData()
            httpData.readCardsNames()
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            var adapter = liV?.adapter as ArrayAdapter<String>
            if (liV != null) {
                adapter.notifyDataSetChanged()
            }
        }
    }
}
