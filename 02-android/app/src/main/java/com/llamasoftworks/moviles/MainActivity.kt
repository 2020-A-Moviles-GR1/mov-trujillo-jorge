package com.llamasoftworks.moviles

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Activity", "OnCreate")
        btn_ciclo_vida.setOnClickListener{ boton->
            irCicloDeVida()
        }

        btn_list_view
            .setOnClickListener { boton ->
                irListView()
            }

        btn_int_respuesta
            .setOnClickListener { boton ->
                irAIntentConRespuesta()
            }

        btn_intent_implicito
            .setOnClickListener {
                enviarIntentConRespuesta()
            }

        btn_resp_propia
            .setOnClickListener {
                enviarIntentConRespuestaPropia()
            }
    }

    fun enviarIntentConRespuestaPropia(){
        val intentExplicito = Intent(
            this,
            IntentEnviaParametros::class.java
        )
        startActivityForResult(intentExplicito,305)
    }


    override fun onActivityResult(requestCode: Int, //NUmero que enviamos
                                  resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode){
            Activity.RESULT_OK->{
                Log.i("resultado","OK")
                when(requestCode){
                    304->{//Contactos
                        val uri = data?.data
                        val cursor = contentResolver.query(
                            uri, null, null, null, null, null
                        )
                        cursor?.moveToFirst()
                        val indiceTelefono = cursor?.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                        val telefono = cursor?.getString(indiceTelefono!!)
                        cursor?.close()
                        Log.i("resultado", "Telefono: ${telefono}")
                    }
                    305 ->{
                        if(data!=null){
                            val nombre = data.getStringExtra("nombre")
                            val edad = data.getIntExtra("edad", 0)
                            Log.i("resultado", "Nombre: ${nombre} Edad: ${edad}")
                        }
                    }
                }
            }
            Activity.RESULT_CANCELED->{
                Log.i("resultado",":'v")
            }
        }

    }


    fun enviarIntentConRespuesta(){
        val intentConRespuesta =  Intent(
            Intent.ACTION_PICK,
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        )
        //this.startActivityForResult(intent, codigo de respuesta)
        //304 es un numero cualquiera que pusimos nosotros
        startActivityForResult(intentConRespuesta, 304)
    }

    fun irAIntentConRespuesta(){
        val intentExplicito = Intent(
            this,
            IntentEnviaParametros::class.java
        )
        intentExplicito.putExtra("numero", 2)
        startActivity(intentExplicito)
    }

    fun irListView(){
        val intentExplicito = Intent(
            this,
            ListViewActivity::class.java
        )
        startActivity(intentExplicito)
    }

    fun irCicloDeVida(){
        val intentExplicito = Intent(
            this,
            CicloVida::class.java
        )
        startActivity(intentExplicito)
    }
}
