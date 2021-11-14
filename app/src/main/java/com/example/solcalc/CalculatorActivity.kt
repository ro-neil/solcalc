package com.example.solcalc

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.Switch
import androidx.lifecycle.ViewModelProvider
import com.example.solcalc.model.EstimateViewModel
import com.example.solcalc.model.client.Client
import com.example.solcalc.model.estimate.Estimate
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.TextInputEditText

class CalculatorActivity : AppCompatActivity() {

    lateinit var estimateViewModel: EstimateViewModel
    lateinit var calc: CalculationsApi
    lateinit var quote: CalculationsApi.SolarQuote

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val editName: TextInputEditText = findViewById(R.id.client_name)
        val editAddress: TextInputEditText = findViewById(R.id.client_addr)
        val slider: Slider = findViewById(R.id.billCut)
        val batteries: Switch = findViewById(R.id.ifBatteries)
        val editBill: TextInputEditText = findViewById(R.id.client_bill)


        val getEstimateBtn: Button = findViewById(R.id.get_estimate)
        getEstimateBtn.setOnClickListener{
            //var intent = Intent()   // Create implicit Intent
            val name = editName.text.toString()
            val address = editAddress.text.toString()
            val stringBill : Float = java.lang.Float.valueOf(editBill.text.toString())
            val billCutVal = slider.value.toDouble()/100    // 0 <= billCutVal <= 1
            val batteriesBool = batteries.isChecked
            val bill = stringBill.toDouble()

            if(TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || (bill == 0.0)){
                Log.d("CalculatorActivity","Name, Address or Bill field is empty")
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
                val d : String = java.lang.String.valueOf(numberOfPanels)
                Log.d("no", d)
                val inverterCapacity: Double = quote.inverterCapacity
                val storageCapacity: Double = quote.storageCapacity
                val payBackPeriod: Double = quote.payBackPeriod
                val totalInstallationCost: Double = quote.totalInstallationCost

                // Create Client & Estimate or Update Client Estimates
                //val client = Client(cid=0,name,address)
                //estimateViewModel.insert(client)

//                val data  = estimateViewModel.getLastClient()
//                val tt = estimateViewModel.getSpecificClient(2)
//                Log.d("test5", tt.name)
//                val data2 = data.value
//                val data3 = data2?.get(0)?.name
//                Log.d("test2", data3.toString())

//                Log.d("CalculatorActivity","Creating Estimate entity")
//                val estimate = Estimate(eid=0, cid,numberOfPanels,inverterCapacity, storageCapacity,billCutVal,payBackPeriod, totalInstallationCost)
//                Log.d("CalculatorActivity","Created Estimate entity(eid: ${estimate.eid}). Inserting into DB...")
//                estimateViewModel.insert(estimate)
//
                // Recall and use data from DB
                Log.d("CalculatorActivity","Creating Intent")
                val intent = Intent(this@CalculatorActivity, EstimateActivity::class.java) // Reassign to explicit intent
                // Pass estimate values as extras to EstimateActivity
                Log.d("CalculatorActivity","Intent created. Putting extras...")
                intent.putExtra(CLIENT_NAME, name)
                intent.putExtra(CLIENT_ADDRESS, address)
                intent.putExtra(PANELS, numberOfPanels)
                intent.putExtra(INVERTER_SIZE, inverterCapacity)
                intent.putExtra(STORAGE_SIZE, storageCapacity)
                intent.putExtra(CLIENT_BILL_CUT, billCutVal)
                intent.putExtra(PAYBACK_PERIOD, payBackPeriod)
                intent.putExtra(TOTAL_COST, totalInstallationCost)
                Log.d("Intent","Intent extras assigned. Starting EstimateActivity...")
                 //Start estimate activity to format the results
                startActivity(intent);
            }
            //finish()
        }
    }

    companion object{
        const val CLIENT_NAME = "client_name"
        const val CLIENT_ADDRESS = "client_address"
        const val PANELS = "panels"
        const val INVERTER_SIZE = "inverter_size"
        const val STORAGE_SIZE = "storage_size"
        const val CLIENT_BILL_CUT = "bill_cut"
        const val PAYBACK_PERIOD = "payback_size"
        const val TOTAL_COST = "total_cost"

        /*const val CLIENT_BILL = "client_bill"
        const val CLIENT_IF_BATTERIES = "batteries_desc"*/

    }
}