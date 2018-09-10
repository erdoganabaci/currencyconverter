package com.example.erdo.currencyconverter

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import org.json.JSONObject
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun getRates(view : View){
        val download=Download()
        try {
            val url="http://data.fixer.io/api/latest?access_key=abc0843ae1c062b796a9f3cdab7229b8&format=1"
            download.execute(url)
        }catch (e : Exception)
        {
            e.printStackTrace()
        }
    }

    inner class Download : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String {
            //inner class olmasının sebebi arka planda veri indirdiğinden öbür türlü oncreatede çalışcaa cok uzun beklemeler olacaktır.
            var result =""
            var url :URL
            var httpConnection : HttpURLConnection
            try { //param string dizisidir.
                url=URL(params[0])
                httpConnection=url.openConnection() as HttpURLConnection
                val inputStream=httpConnection.inputStream //gelen verileri okumayı sağlıyor
                val inputStreamReader=InputStreamReader(inputStream)
                var data=inputStreamReader.read()
                while (data>0){
                    val character=data.toChar()
                    result+=character
                    data=inputStream.read()

                }
                return result
            }catch (e :Exception){
                return result
            }

        }

        override fun onPostExecute(result: String?) {
            println(result)

            /* try {
                 println(result)
                 //val jsonObject=JSONObject(result)
                 //val base=jsonObject.getString("base")
                 //val rates=jsonObject.getString("rates")
                 //println(jsonObject)
                 //println(rates)
                 //val jsonObject1=JSONObject(rates)
                 //val turkish=jsonObject1.getString("TRY")
                //println(turkish)
             }catch (e: Exception){
                 e.printStackTrace()
             }*/
            super.onPostExecute(result)
        }
    }
}
