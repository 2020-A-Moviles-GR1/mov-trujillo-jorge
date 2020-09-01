package com.llamasoftworks.moviles

import android.app.Activity
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
        val yoshi = intent.getParcelableExtra<Mascota>("yoshi")
        if (yoshi != null){
            Log.i("parcelable","${yoshi.nombre}  ${yoshi.duenio?.nombre}")
        }
        val arregloMascota = intent.getParcelableArrayListExtra<Mascota>("arregloMascota")
        if (arregloMascota!= null){
            arregloMascota.forEach {
                Log.i("parcelable","EN ARREGLO")
                Log.i("parcelable","${it.nombre}  ${it.duenio?.nombre}")
            }
        }
        buttonbtn_devolver_respuesta.setOnClickListener {
            finish()
        }

        btn_resp_aceptar
            .setOnClickListener {
                val nombre = "Jorge"
                val edad = 31
                val intentRespuesta = Intent()
                intentRespuesta.putExtra("nombre", nombre)
                intentRespuesta.putExtra("edad", edad)
                //this.setResult()
                setResult(
                    RESULT_OK,
                    intentRespuesta
                )
                //this.finish()
                finish()
            }

        btn_resp_cancelar
            .setOnClickListener {
                val intentCancelado = Intent()
                setResult(
                    RESULT_CANCELED,
                    intentCancelado
                )
                finish()
            }
    }
}
