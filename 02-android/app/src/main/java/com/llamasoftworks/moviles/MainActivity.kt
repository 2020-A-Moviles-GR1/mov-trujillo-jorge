package com.llamasoftworks.moviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
