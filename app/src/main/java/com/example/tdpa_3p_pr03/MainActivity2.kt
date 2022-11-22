package com.example.tdpa_3p_pr03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val t = findViewById<TextView>(R.id.total)
        val bundle = intent.extras
        val total = bundle?.getString("total")
        t.text = "$${total}"
    }

}