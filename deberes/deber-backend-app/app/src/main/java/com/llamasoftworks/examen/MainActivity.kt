package com.llamasoftworks.examen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.nio.charset.Charset


class MainActivity : AppCompatActivity() {

    companion object{
        var responseList: List<String>? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = PagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        responseList = getAllCardNames()
    }

    fun getAllCardNames(): List<String>? {
        var reader: JSONArray? = null
        var responseList: MutableList<String> = ArrayList()
        try {
            reader = JSONArray(readJSONFromAsset())
            System.out.println(reader)
            for (i in 0 until reader!!.length()) {
                val e = reader.getJSONObject(i)
                val name = e.getString("name")
                responseList.add(name)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        println(responseList[0])
        return responseList
    }

    private fun readJSONFromAsset(): String? {
        var json: String? = null
        val inputStream = this.assets.open("allNameCards.json")
        try {
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.defaultCharset())
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
   }
