package com.example.solcalc

import android.content.Intent
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

        // Get explicit intent from activity_calculator or previous_estimates
        // Retrieve the precalculated/prefetched values for insertion into TextViews
        // Eliminating the need to know the source of the intent. Instead, just present the values given

        // Get extras
        val clientsName: String = intent.getStringExtra(CalculatorActivity.CLIENT_NAME).toString()
        val clientsAddress: String = intent.getStringExtra(CalculatorActivity.CLIENT_ADDRESS).toString()
        val solarPanels: String = intent.getIntExtra(CalculatorActivity.PANELS,0).toString()
        val inverterSize: String = intent.getDoubleExtra(CalculatorActivity.INVERTER_SIZE,0.0).toString() + " kW"
        val storageSize: String = intent.getDoubleExtra(CalculatorActivity.STORAGE_SIZE,0.0).toString() + " kWh"
        val billCut: String = (intent.getDoubleExtra(CalculatorActivity.CLIENT_BILL_CUT,0.0) * 100 ).toString() + "%"
        val paybackPeriod: String = intent.getDoubleExtra(CalculatorActivity.PAYBACK_PERIOD,0.0).toString() + " yrs"
        val totalCost: String = "$" + intent.getDoubleExtra(CalculatorActivity.TOTAL_COST,0.0).toString()

        // Set TextView References
        val clientName: TextView = findViewById<TextView?>(R.id.ClientName)
        val clientAddress: TextView = findViewById<TextView?>(R.id.ClientAddress)
        val noSolarPanels: TextView = findViewById<TextView?>(R.id.no_of_panels)
        val calcInverterSize: TextView = findViewById<TextView?>(R.id.inverter_size)
        val storage: TextView = findViewById<TextView?>(R.id.storage)
        val billCutBy: TextView = findViewById<TextView?>(R.id.bill_cut)
        val paybackPeriodIs: TextView = findViewById<TextView?>(R.id.payback_period)
        val totalInstallationCost: TextView = findViewById<TextView?>(R.id.total_sys_cost)

        // Update TextView References
        clientName.text = clientsName
        clientAddress.text = clientsAddress
        noSolarPanels.text = solarPanels
        calcInverterSize.text = inverterSize
        storage.text = storageSize
        billCutBy.text = billCut
        paybackPeriodIs.text = paybackPeriod
        totalInstallationCost.text = totalCost
    }
}