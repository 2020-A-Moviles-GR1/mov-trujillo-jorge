package com.llamasoftworks.examen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import java.net.URL


class RecyclerAdaptador(
    private val listaUsuarios: List<Carta>,
    private val conexto:FragmentActivity,
    private val recyclerView:RecyclerView
) : RecyclerView.Adapter<RecyclerAdaptador.MyViewHolder>(){
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var nombreTextView: TextView
        var idTextView: TextView
        var imageView: ImageView
        var priceTextView:TextView
        var tcgTextView:TextView
        init{
            nombreTextView = view.findViewById(R.id.tv_card_name_fr)
            idTextView = view.findViewById(R.id.tv_card_id_fr)
            imageView = view.findViewById(R.id.imageView)
            priceTextView = view.findViewById(R.id.tv_precio)
            tcgTextView = view.findViewById(R.id.tv_tcg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //Definimos la interfaz a usar
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.adapter_carta,
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
        val carta = listaUsuarios[position]
        holder.nombreTextView.text = carta.nombre
        holder.idTextView.text = carta.id
        if (carta.tcg) holder.tcgTextView.text = "Disponible en TCG" else
            holder.tcgTextView.text ="NO disponible en TCG"
        holder.priceTextView.text = "Precio: " + carta.precio
        printImage(carta.image_url,holder)
    }

    fun printImage(url:String, holder: MyViewHolder){
        class Tarea:AsyncTask<Void,Void,Bitmap>(){
            override fun doInBackground(vararg p0: Void?): Bitmap? {
                val urldisplay: String = url
                var mIcon11: Bitmap? = null
                try {
                    val inputStream = URL(urldisplay).openStream()
                    mIcon11 = BitmapFactory.decodeStream(inputStream)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return mIcon11
            }

            override fun onPostExecute(result: Bitmap?) {
                holder.imageView.setImageBitmap(result)
            }
        }
        Tarea().execute()
    }


}