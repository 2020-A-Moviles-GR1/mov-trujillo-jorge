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
            this.irExpActivity()
        }
    }

    override fun onStart() {
        super.onStart()
        MyTask(activity).execute()
        var adapter = ArrayAdapter(
            activity, android.R.layout.simple_list_item_1,HttpData.expansionesList
        )
        lv_expanciones.adapter = adapter
        adapter.notifyDataSetChanged()
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
        var liV = activityReference.get()?.findViewById<ListView>(R.id.lv_expanciones)
        override fun doInBackground(vararg p0:Void): Void? {
            val httpDataExp = HttpDataExp()
            httpDataExp.readExpansionNames()
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
