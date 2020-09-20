package com.llamasoftworks.examen

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_cartas.*


class CartasFragment : Fragment(){
    companion object{
        var cardNum = -1
    }

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
        class MyTask() : AsyncTask<Void, Void?, Void?>() {
            override fun doInBackground(vararg p0:Void): Void? {
                HttpData().readCardsNames()
                return null
            }
            override fun onPostExecute(result: Void?) {
                activity?.let {
                    iniciarRecyclerView(
                        HttpData.cartasList, it,rv_cartas
                    )
                }
            }
        }
        MyTask().execute()
    }
}
