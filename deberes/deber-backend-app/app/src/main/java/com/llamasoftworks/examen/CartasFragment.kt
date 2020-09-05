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
        Log.i("http-klaxon","on create ")
        return inflater.inflate(R.layout.fragment_cartas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("http-klaxon","onViwr creayed")
        super.onViewCreated(view, savedInstanceState)
        fab_add_card.setOnClickListener{
            irCartaActivity()
        }

    }

    override fun onStart() {
        super.onStart()
        Log.i("http-klaxon","Start")
        lv_cartas.adapter= ArrayAdapter(
            activity, android.R.layout.simple_list_item_1,HttpData.cartasList
        )
        MyTask(activity).execute()
        lv_cartas.deferNotifyDataSetChanged()
        lv_cartas
            .onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            cardNum = position
            irCartaActivityEdit()}
    }

    override fun onResume() {
        super.onResume()
        Log.i("http-klaxon","Resumr")
        MyTask(activity).execute()
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

    private class MyTask(context: FragmentActivity?) : AsyncTask<Void, Void?, ArrayList<String>>() {
        val activityReference: WeakReference<FragmentActivity?> = WeakReference(context)
        var liV = activityReference.get()?.findViewById<ListView>(R.id.lv_cartas)
        override fun doInBackground(vararg p0:Void): ArrayList<String> {
            val httpData = HttpData()

            return httpData.readCardsNames()
        }
        override fun onPostExecute(aVoid: ArrayList<String>) {
            HttpData.cartasList= aVoid
            (liV!!.adapter as ArrayAdapter<String>).notifyDataSetChanged()

        }
    }
}
