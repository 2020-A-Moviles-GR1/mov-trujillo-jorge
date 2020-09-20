package com.llamasoftworks.examen

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import java.net.URL


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var latlong: LatLng
    private var url = ""
    private var nombre = ""
    private var image_url = ""
    var expansion = -1
    override fun onStart() {
        super.onStart()
        val policy =
            StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val numeroEncontrado = intent.getIntExtra("carta", -1)
        expansion = intent.getIntExtra("expansion", -1)
        if (numeroEncontrado!=-1){
            val carta = HttpData.cartasList[numeroEncontrado]
            latlong = LatLng(carta.lat,carta.long)
            url = carta.url
            image_url = carta.image_url
            nombre = carta.nombre
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        establecerConfiguracionMapa(mMap)
        establecerListeners(mMap)
        //moverCamaraZoom(latlong,17f)

        if (url!=""){
            val sydney = latlong
            mMap.addMarker(MarkerOptions().position(sydney).icon(getBitmap(image_url)).title(nombre).snippet(url))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        }else{
            HttpDataExp.cartasOnExp.forEach{
                mMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(it.lat,it.long))
                        .icon(getBitmap(it.image_url))
                        .title(it.nombre)
                        .snippet(it.url)
                )
            }
        }
    }

    override fun onInfoWindowClick(p0: Marker?) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(p0?.snippet))
        startActivity(browserIntent)
    }

    fun establecerListeners(map: GoogleMap){
        with(map){
            setOnInfoWindowClickListener(this@MapsActivity)
        }
    }

    fun getBitmap(url:String): BitmapDescriptor {
        var bmp = BitmapFactory.decodeStream(URL(url).openConnection().getInputStream())
        bmp = Bitmap.createScaledBitmap(bmp, 140, 204, false)
         return BitmapDescriptorFactory.fromBitmap(bmp)
    }

    fun establecerConfiguracionMapa(mapa:GoogleMap){
        val contexto = this.applicationContext
        with(mapa){
            val permisosFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if(tienePermisos){
                mapa.isMyLocationEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }
    }
}
