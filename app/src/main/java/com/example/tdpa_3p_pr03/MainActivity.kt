package com.example.tdpa_3p_pr03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private val indicadoresList = mutableListOf<Indicador>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mxn = findViewById<EditText>(R.id.mxn)
        var btn = findViewById<Button>(R.id.btn)
        getData()
        fun validar():Boolean{
            if(TextUtils.isEmpty(mxn.text.toString())){
                mxn.error = "Capture una cantidad"
                return false
            }
            return true
        }
        btn.setOnClickListener {
            if (validar()){
                try {
                    val intento = Intent(this, MainActivity2::class.java)
                    var valor = indicadoresList[0].valor.toFloat()
                    var total = mxn.text.toString().toFloat() / valor
                    intento.putExtra("total", total.toString())
                    //Toast.makeText(this,"Total: $total", Toast.LENGTH_SHORT).show()
                    startActivity(intento)
                } catch (e: Exception){
                    print(e)
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://sidof.segob.gob.mx/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getData(){
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val call = getRetrofit().create(APIService::class.java).getIndicadores()
                val indc = call.body()
                runOnUiThread(){
                    if (call.isSuccessful){
                        val listIndicadores = indc?.Indicadores?: emptyList()
                        indicadoresList.clear()
                        indicadoresList.addAll(listIndicadores)
                    } else {
                        showError()
                    }
                }
            }
        } catch (e:Exception){
            print(e)
        }
    }
    private fun showError() {
        Toast.makeText(this,"Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }
}