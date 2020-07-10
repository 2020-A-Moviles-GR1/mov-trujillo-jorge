package clases

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONObject

class Datos{

    fun fromApi(): String? {
        val url = URL("https://db.ygoprodeck.com/randomSearch.php")
        try {
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.addRequestProperty("User-Agent", "Mozilla/4.0")
            val input: InputStream
            input = if (connection.getResponseCode() === 200) // this must be called before 'getErrorStream()' works
                connection.getInputStream() else connection.getErrorStream()
            val reader = BufferedReader(InputStreamReader(input))
            var msg: String?
            while (reader.readLine().also { msg = it } != null) return (msg)
        } catch (e: IOException) {
            System.err.println(e)
            return ""
        }
        return ""
    }

    fun strToJson():List<*>{
        var d = JSONObject(fromApi())
        return listOf(d["name"] as String, d["id"])
    }


}