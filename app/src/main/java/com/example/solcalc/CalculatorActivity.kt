package com.example.solcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.TextInputLayout

class CalculatorActivity : AppCompatActivity() {

    private lateinit var editName: TextInputLayout
    private lateinit var editAddress: TextInputLayout
    private lateinit var slider: Slider
    private lateinit var batteries:TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        editName = findViewById(R.id.client_name)
        editAddress = findViewById(R.id.client_addr)

        slider = findViewById(R.id.billCut)


        batteries = findViewById(R.id.if_batteries)
        val getEstimate = findViewById<Button>(R.id.get_estimate)
        getEstimate.setOnClickListener {
            val name = editName.editText?.text.toString()
            val address = editAddress.editText?.text.toString()
        }

    }
}