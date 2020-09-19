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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_cartas.*
import java.lang.ref.WeakReference


class CartasFragment : Fragment(){
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
        tarea()
//        lv_cartas
//            .onItemClickListener = AdapterView.OnItemClickListener {
//                parent, view, position, id ->
//            cardNum = position
//            irCartaActivityEdit()}
    }

    override fun onResume() {
        super.onResume()
        Log.i("http-klaxon","Resumr")
        tarea()
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

    fun iniciarRecyclerView(
        list: List<Carta>,
        activity: FragmentActivity,
        recyclerView: RecyclerView
    ){
        val adaptadorUsuario = RecyclerAdaptador(
            list,
            activity,
            recyclerView
        )
        rv_cartas.adapter = adaptadorUsuario
        rv_cartas.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rv_cartas.layoutManager = LinearLayoutManager(activity)
        adaptadorUsuario.notifyDataSetChanged()
    }

    fun tarea(){
        class MyTask(context: FragmentActivity?) : AsyncTask<Void, Void?, ArrayList<Carta>>() {
            var context = context
            override fun doInBackground(vararg p0:Void): ArrayList<Carta> {
                val httpData = HttpData()
                return httpData.readCardsNames()
            }
            override fun onPostExecute(aVoid: ArrayList<Carta>) {
                HttpData.cartasList= aVoid
                context?.let {
                    iniciarRecyclerView(
                        HttpData.cartasList, it,rv_cartas
                    )
                }
            }
        }
        MyTask(this.activity).execute()
    }


}
