package com.example.solcalc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.solcalc.model.EstimateViewModel
import com.example.solcalc.model.client.Client
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    lateinit var estimateViewModel: EstimateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editClientName: TextView = findViewById(R.id.ClientName)
        val editClientAddress: TextView = findViewById(R.id.client_address)
        val editClientTotal: TextView = findViewById(R.id.client_total)

        val editClientName2: TextView = findViewById(R.id.ClientName2)
        val editClientAddress2: TextView = findViewById(R.id.client_address2)
        val editClientTotal2: TextView = findViewById(R.id.client_total2)

        val editClientName3: TextView = findViewById(R.id.ClientName3)
        val editClientAddress3: TextView = findViewById(R.id.client_address3)
        val editClientTotal3: TextView = findViewById(R.id.client_total3)

        estimateViewModel = ViewModelProvider(this@MainActivity).get(EstimateViewModel::class.java)

        // Get Last Three Clients
        val clients = estimateViewModel.getLastThreeClients()

        if(clients.isNotEmpty()) {

            // Store reference to Client 1 retrieved from DB
            var clientName: String = clients[0].name
            var clientAddress: String = clients[0].addr
            var clientTotal: Double = estimateViewModel.getEstimate(clients[0].cid).totalCost

            // Update UI with DB values
            editClientName.text = clientName
            editClientAddress.text = clientAddress
            editClientTotal.text = clientTotal.toString()

            try {
                // Store reference to Client 2 retrieved from DB
                clientName = clients[1].name
                clientAddress = clients[1].addr
                clientTotal = estimateViewModel.getEstimate(clients[1].cid).totalCost

                // Update UI with DB values
                editClientName2.text = clientName
                editClientAddress2.text = clientAddress
                editClientTotal2.text = clientTotal.toString()
            } catch (e: Exception){
                editClientName2.text = "-"
                editClientAddress2.text = "-"
                editClientTotal2.text = "-"
            }

            try {
                // Store reference to Client 3 retrieved from DB
                clientName = clients[2].name
                clientAddress = clients[2].addr
                clientTotal = estimateViewModel.getEstimate(clients[2].cid).totalCost

                // Update UI with DB values
                editClientName3.text = clientName
                editClientAddress3.text = clientAddress
                editClientTotal3.text = clientTotal.toString()
            } catch(e: Exception){
                editClientName3.text = "-"
                editClientAddress3.text = "-"
                editClientTotal3.text = "-"
            }
        } else {
            editClientName.text = "-"
            editClientAddress.text = "-"
            editClientTotal.text = "-"
            editClientName2.text = "-"
            editClientAddress2.text = "-"
            editClientTotal2.text = "-"
            editClientName3.text = "-"
            editClientAddress3.text = "-"
            editClientTotal3.text = "-"
        }


        val calcBtn : Button = findViewById(R.id.calc_button)
        calcBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, CalculatorActivity::class.java)
            startActivity(intent);
        }
    }
}