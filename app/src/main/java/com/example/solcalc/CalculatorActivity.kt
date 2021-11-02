package com.example.solcalc

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Switch
import com.example.solcalc.model.EstimateViewModel
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text
import java.security.KeyStore

class CalculatorActivity : AppCompatActivity() {

    lateinit var estimateViewModel: EstimateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val editName: TextInputEditText = findViewById(R.id.client_name)
        val editAddress: TextInputEditText = findViewById(R.id.client_addr)
        val slider: Slider = findViewById(R.id.billCut)
        val batteries: Switch = findViewById(R.id.ifBatteries)


        val getEstimateBtn: Button = findViewById(R.id.get_estimate)
        getEstimateBtn.setOnClickListener{
            val genEstimateIntent = Intent()
            val name = editName.text.toString()
            val address = editAddress.text.toString()
            val billCutVal = slider.value.toInt()
            val batteriesBool = batteries.isChecked

            if(TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || billCutVal == null){
                setResult(Activity.RESULT_CANCELED, genEstimateIntent)
            }else{
                genEstimateIntent.putExtra(CLIENT_NAME, name)
                genEstimateIntent.putExtra(CLIENT_ADDR, address)
                genEstimateIntent.putExtra(CLIENT_BILL_CUT, billCutVal)
                genEstimateIntent.putExtra(CLIENT_IF_BATTERIES, batteriesBool)
                setResult(Activity.RESULT_OK,genEstimateIntent)
            }
            finish()
        }
    }

    companion object{
        const val CLIENT_NAME = "client_name"
        const val CLIENT_ADDR = "client_address"
        const val CLIENT_BILL_CUT = "bill_cut"
        const val CLIENT_IF_BATTERIES = "batteries_desc"
    }
}