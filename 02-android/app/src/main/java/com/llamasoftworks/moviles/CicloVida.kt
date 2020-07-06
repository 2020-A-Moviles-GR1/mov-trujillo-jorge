package com.llamasoftworks.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_ciclo_vida.*

class CicloVida : AppCompatActivity() {

    var numeroActual =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciclo_vida)
        Log.i("Activity", "OnCreate")
        btn_anadir
            .setOnClickListener{
                sumarUnValor()
            }

    }

    fun sumarUnValor(){
        numeroActual = numeroActual +1
        tv_numero.text= numeroActual.toString()
    }

    override fun onStart() {
        super.onStart()
        Log.i("Activity","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Activity","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Activity","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Activity","onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Activity","onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Activity","onDestroy")
    }
}
