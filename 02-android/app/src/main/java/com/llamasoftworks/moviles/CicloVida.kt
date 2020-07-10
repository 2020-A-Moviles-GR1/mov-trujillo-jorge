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
        Log.i("sctivity", "OnCreate")
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
        Log.i("activity","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("activity","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("activity","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("activity","onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("activity","onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("activity","onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("activity", "onSaveInstanceState")
        outState?.run {
            putInt("numeroActualGuardado", numeroActual)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("activity", "onRestoreInstanceState")
        val valorRecuperado = savedInstanceState
            ?.getInt("numeroActualGuardado")
            if (valorRecuperado != null){
                this.numeroActual = valorRecuperado
                tv_numero.text = this.numeroActual.toString()
            }
    }
}
