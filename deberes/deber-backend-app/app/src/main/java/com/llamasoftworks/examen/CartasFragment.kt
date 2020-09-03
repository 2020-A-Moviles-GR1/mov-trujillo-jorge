package com.llamasoftworks.examen

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.fragment_cartas.*
import java.lang.ref.WeakReference


class CartasFragment : Fragment() {
    var cardNum = -1

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
        Log.i("http-klaxon","OnStart ${HttpData.cartasList} ")
    }

    override fun onStart() {
        super.onStart()
        MyTask(activity).execute()
        lv_cartas.adapter = ArrayAdapter(
            activity, android.R.layout.simple_list_item_1,HttpData.cartasList
        )
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

    private class MyTask(context: FragmentActivity?) : AsyncTask<Void, Void?, Void?>() {
        val activityReference: WeakReference<FragmentActivity?> = WeakReference(context)
        var liV = activityReference.get()?.findViewById<ListView>(R.id.lv_cartas)
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
