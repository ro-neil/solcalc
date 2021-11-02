package com.example.solcalc.model

import androidx.lifecycle.LiveData
import com.example.solcalc.model.client.Client
import com.example.solcalc.model.client.ClientDao
import com.example.solcalc.model.estimate.Estimate
import com.example.solcalc.model.estimate.EstimateDao

class SolCalcRepo (private val clientDao: ClientDao, private val estimateDao: EstimateDao){

    val clients: LiveData<List<Client>> = clientDao.getLastThreeEntries()

    suspend fun insert(client: Client){
        clientDao.insertClient(client)
    }

    suspend fun insert(estimate: Estimate){
        estimateDao.insertEstimate(estimate)
    }
}