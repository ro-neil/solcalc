package com.example.solcalc

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Switch
import androidx.lifecycle.ViewModelProvider
import com.example.solcalc.model.EstimateViewModel
import com.example.solcalc.model.client.Client
import com.example.solcalc.model.estimate.Estimate
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text

class CalculatorActivity : AppCompatActivity() {

    lateinit var estimateViewModel: EstimateViewModel
    lateinit var calc: CalculationsApi
    lateinit var quote: CalculationsApi.SolarQuote

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val editName: TextInputEditText = findViewById(R.id.client_name)
        val editAddress: TextInputEditText = findViewById(R.id.client_addr)
        val editEmail: TextInputEditText = findViewById(R.id.client_email)
        val slider: Slider = findViewById(R.id.billCut)
        val batteries: Switch = findViewById(R.id.ifBatteries)
        val editBill: TextInputEditText = findViewById(R.id.client_bill)


        val getEstimateBtn: Button = findViewById(R.id.get_estimate)
        getEstimateBtn.setOnClickListener{
            val name = editName.text.toString()
            val address = editAddress.text.toString()
            val email = editEmail.text.toString()
            val stringBill : Float = java.lang.Float.valueOf(editBill.text.toString())
            val billCutVal = slider.value.toDouble()/100
            val batteriesBool = batteries.isChecked
            val bill = stringBill.toDouble()

            if(TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(email) || (bill == 0.0)){
                setResult(Activity.RESULT_CANCELED, intent)
            }else{
                calc = CalculationsApi()
                estimateViewModel = ViewModelProvider(this@CalculatorActivity).get(EstimateViewModel::class.java)

                // Generate quote based on battery preference
                quote = if(batteriesBool){
                    calc.getTotalSystemCostWithBatteries(bill, billCutVal)
                } else {
                    calc.getTotalSystemCostWithoutBatteries(bill, billCutVal)
                }

                // Store quote fields as references
                val numberOfPanels: Int = quote.numberOfPanels
                val inverterCapacity: Double = "%.2f".format(quote.inverterCapacity).toDouble()
                val storageCapacity: Double = "%.2f".format(quote.storageCapacity).toDouble()
                val payBackPeriod: Double = "%.2f".format(quote.payBackPeriod).toDouble()
                val totalInstallationCost: Double = "%.2f".format(quote.totalInstallationCost).toDouble()

                //Create Client & Estimate or Update Client Estimates
                val client = Client(cid=0,name,address, email)
                estimateViewModel.insert(client)

                val data  = estimateViewModel.getLastClient()
                val cid = data[0].cid

                val estimate = Estimate(eid=0, cid,numberOfPanels,inverterCapacity, storageCapacity,billCutVal,payBackPeriod, totalInstallationCost)
                estimateViewModel.insert(estimate)

                // Recall and use data from DB
                val intent = Intent(this@CalculatorActivity, EstimateActivity::class.java) // Reassign to explicit intent
                // Pass estimate values as extras to EstimateActivity
                intent.putExtra(CLIENT_NAME, name)
                intent.putExtra(CLIENT_ADDRESS, address)
                intent.putExtra(CLIENT_EMAIL, email)
                intent.putExtra(PANELS, numberOfPanels)
                intent.putExtra(INVERTER_SIZE, inverterCapacity)
                intent.putExtra(STORAGE_SIZE, storageCapacity)
                intent.putExtra(CLIENT_BILL_CUT, billCutVal)
                intent.putExtra(PAYBACK_PERIOD, payBackPeriod)
                intent.putExtra(TOTAL_COST, totalInstallationCost)

                //Start estimate activity to format the results
                startActivity(intent);
            }
        }

        val backBtn : ImageButton = findViewById(R.id.back_arrow)
        backBtn.setOnClickListener {
            val intent = Intent(this@CalculatorActivity, MainActivity::class.java)
            startActivity(intent);
        }

    }

    companion object{
        const val CLIENT_NAME = "client_name"
        const val CLIENT_ADDRESS = "client_address"
        const val CLIENT_EMAIL = "client_email"
        const val PANELS = "panels"
        const val INVERTER_SIZE = "inverter_size"
        const val STORAGE_SIZE = "storage_size"
        const val CLIENT_BILL_CUT = "bill_cut"
        const val PAYBACK_PERIOD = "payback_size"
        const val TOTAL_COST = "total_cost"
    }
}