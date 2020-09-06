package com.llamasoftworks.examen

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.fragment_expansiones.*
import java.lang.ref.WeakReference

class ExpansionesFragment : Fragment() {

    var expNum = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_expansiones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab_add_exp.setOnClickListener {
            irExpActivity()
        }
        MyTask(activity).execute()
    }

    override fun onStart() {
        super.onStart()
        lv_expanciones
            .onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            expNum = position
            irExpansionActivityEdit()}
    }

    fun irExpActivity(){
        val intentExplicito = Intent(
            activity,
            ExpansionActivity::class.java
        )
        startActivity(intentExplicito)
    }

    override fun onResume() {
        super.onResume()
        MyTask(activity).execute()
    }

    fun irExpansionActivityEdit(){
        val intentExplicito = Intent(
            activity,
            ExpansionActivity::class.java
        )
        intentExplicito.putExtra("numero", expNum)
        startActivity(intentExplicito)
    }

    private class MyTask(context: FragmentActivity?) : AsyncTask<Void, Void?, Void?>() {
        val activityReference: WeakReference<FragmentActivity?> = WeakReference(context)
        val context = context
        var liV = activityReference.get()?.findViewById<ListView>(R.id.lv_expanciones)
        override fun doInBackground(vararg p0:Void): Void? {
            val httpDataExp = HttpDataExp()
            httpDataExp.readExpansionNames()
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            var adaptador = ArrayAdapter(
                context, android.R.layout.simple_list_item_1,HttpDataExp.expansionesList
            )
            liV?.adapter = adaptador
            adaptador.notifyDataSetChanged()
        }
    }

}
