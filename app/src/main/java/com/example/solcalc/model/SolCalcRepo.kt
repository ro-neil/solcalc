package com.example.solcalc.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.solcalc.model.client.Client
import com.example.solcalc.model.client.ClientDao
import com.example.solcalc.model.estimate.Estimate
import com.example.solcalc.model.estimate.EstimateDao

class SolCalcRepo (private val clientDao: ClientDao, private val estimateDao: EstimateDao){

    val clients: LiveData<List<Client>> = clientDao.getAllClients()

    suspend fun insert(client: Client){
        clientDao.insertClient(client)
    }

    suspend fun insert(estimate: Estimate){
        estimateDao.insertEstimate(estimate)
    }

    fun getLastClient(): LiveData<List<Client>> {
        return clientDao.getLastClient()
    }

    fun getSpecificClient(cid:Int): Client {
        return clientDao.getSpecificClient(cid)
    }

}

