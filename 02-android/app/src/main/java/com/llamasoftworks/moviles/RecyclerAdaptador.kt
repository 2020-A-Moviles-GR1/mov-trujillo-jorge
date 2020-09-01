package com.llamasoftworks.moviles

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdaptador(
    private val listaUsuarios: List<UsuarioHttp>,
    //private val conexto
    private val recyclerView:RecyclerView
) : RecyclerView.Adapter<RecyclerAdaptador.MyViewHolder>(){
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}