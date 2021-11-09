package com.example.solcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.solcalc.model.EstimateViewModel

class EstimateActivity : AppCompatActivity() {

    lateinit var estimateViewModel: EstimateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estimate)

        estimateViewModel = ViewModelProvider(this).get(EstimateViewModel::class.java)

        Log.d("New Activity","EstimateActivity started")
        // Get explicit intent from activity_calculator or previous_estimates
        val intent = intent
        Log.d("Intent","Received intent from calling activity")

        // Retrieve the precalculated/prefetched values for insertion into TextViews
        // Eliminating the need to know the source of the intent. Instead, just present the values given

        // Get extras
        val clientsName: String = intent.getBundleExtra("CLIENT_NAME").toString()
        val clientsAddress: String = intent.getBundleExtra("CLIENT_ADDR").toString()
        val creationDate: String = intent.getBundleExtra("CREATION_DATE").toString()
        val solarPanels: String = intent.getBundleExtra("SOLAR_PANELS").toString()
        val inverterSize: String = intent.getBundleExtra("INVERTER_SIZE").toString()
        val storageSize: String = intent.getBundleExtra("STORAGE_SIZE").toString()
        val billCut: String = intent.getBundleExtra("CLIENT_BILL_CUT").toString()
        val paybackPeriod: String = intent.getBundleExtra("PAYBACK_PERIOD").toString()

        Log.d("test", paybackPeriod)

        // Set TextView References
        val clientName: TextView = findViewById<TextView?>(R.id.ClientName)
        val clientAddress: TextView = findViewById<TextView?>(R.id.ClientAddress)
        val date: TextView = findViewById<TextView?>(R.id.date)
        val noSolarPanels: TextView = findViewById<TextView?>(R.id.no_of_panels)
        val calcInverterSize: TextView = findViewById<TextView?>(R.id.inverter_size)
        val storage: TextView = findViewById<TextView?>(R.id.storage)
        val billCutBy: TextView = findViewById<TextView?>(R.id.bill_cut)
        val paybackPeriodIs: TextView = findViewById<TextView?>(R.id.payback_period)

        // Update TextView References
        clientName.text = clientsName
        clientAddress.text = clientsAddress
        date.text = creationDate
        noSolarPanels.text = solarPanels
        calcInverterSize.text = inverterSize
        storage.text = storageSize
        billCutBy.text = billCut
        paybackPeriodIs.text = paybackPeriod

    }
}