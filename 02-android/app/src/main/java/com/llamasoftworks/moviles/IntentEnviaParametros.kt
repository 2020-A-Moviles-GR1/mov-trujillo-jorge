package com.llamasoftworks.moviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_envia_parametros.*
import kotlinx.android.synthetic.main.activity_main.*

class IntentEnviaParametros : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_envia_parametros)
        val numeroEncontrado = intent.getIntExtra("numero", 0)
        if(numeroEncontrado!=0){
            Log.i("intents", "El numero encontrado es ${numeroEncontrado}")
        }

        val textoCompartido:String? = intent.getStringExtra(Intent.EXTRA_TEXT)
        if(textoCompartido!=null){
            Log.i("intents", "El texto compartido es ${textoCompartido}")
        }
        buttonbtn_devolver_respuesta.setOnClickListener {
            finish()
        }
    }
}
