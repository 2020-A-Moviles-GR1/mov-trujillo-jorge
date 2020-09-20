package com.llamasoftworks.moviles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdaptador(
    private val listaUsuarios: List<UsuarioHttp>,
    private val conexto:RecyclerViewActivity,
    private val recyclerView:RecyclerView
) : RecyclerView.Adapter<RecyclerAdaptador.MyViewHolder>(){
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var nombreTextView: TextView
        var cedulaTextView: TextView
        var accionButton: Button
        init{
            nombreTextView = view.findViewById(R.id.tv_nombre)
            cedulaTextView = view.findViewById(R.id.tv_cedula)
            accionButton = view.findViewById(R.id.btn_accion)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //Definimos la interfaz a usar
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.adaptador_persona,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return  listaUsuarios.size
    }

    //una funcion que se ejecuta con cada uno de los items
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val usuario = listaUsuarios[position]
        holder.nombreTextView.text = usuario.nombre
        holder.cedulaTextView.text = usuario.cedula
        holder.accionButton.text = "Like ${usuario.nombre}"
    }

}