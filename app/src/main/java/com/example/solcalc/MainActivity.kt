package com.example.solcalc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)



        val calcBtn : Button = findViewById(R.id.calc_button)
        calcBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, CalculatorActivity::class.java)
            startActivity(intent);
        }
    }
}