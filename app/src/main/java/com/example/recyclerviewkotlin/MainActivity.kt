package com.example.recyclerviewkotlin


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.recyclerviewkotlin.Modelos.Actores
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cargarDatosJSon()


    }

    fun cargarDatosJSon() {
        var listaActores = ArrayList<Actores>()
        val url = "http://iesayala.ddns.net/abus/actores.php"
        val queue = Volley.newRequestQueue(this)
        rv.layoutManager = LinearLayoutManager(this)
        rv.addItemDecoration((DividerItemDecoration(this, DividerItemDecoration.VERTICAL)))

        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener { response ->

            val jsonArray = JSONArray(response)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = JSONObject(jsonArray.getString(i))
                val nombre = jsonObject.get("Nombre").toString()
                val edad = jsonObject.get("Edad").toString()
                val pelicula = jsonObject.get("Pelicula").toString()
                val imagen = jsonObject.get("Imagen").toString()
                var actores = Actores(nombre, edad, pelicula, imagen)
                listaActores.add(actores)
            }
            if (listaActores.size > 0) {
                rv.adapter = adaptador( listaActores)

            } else {
                Toast.makeText(applicationContext, listaActores.size.toString(), Toast.LENGTH_LONG)
                    .show()

            }


        }, Response.ErrorListener {
            Toast.makeText(applicationContext, "Error en la conexion", Toast.LENGTH_LONG).show()
        })

        queue.add(stringRequest)

    }



}