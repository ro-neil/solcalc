package com.example.solcalc.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.solcalc.model.client.Client
import com.example.solcalc.model.estimate.Estimate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EstimateViewModel(application: Application):AndroidViewModel(application) {
   private val repo:SolCalcRepo
   private val clients: LiveData<List<Client>>

   init{
       val db = SolCalcDatabase.getDatabase(application, viewModelScope)
       val clientDao = db.clientDao()
       val estimateDao = db.estimateDao()

       repo = SolCalcRepo(clientDao, estimateDao)
       clients = repo.clients

   }

    fun insert(client: Client) = viewModelScope.launch(Dispatchers.IO){
        repo.insert(client)
    }

    fun insert(estimate: Estimate) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(estimate)
    }

    fun getLastClient(): List<Client> {
        return repo.getLastClient()
    }

    fun getLastThreeClients(): List<Client> {
        return repo.getLastThreeClients()
    }

    fun getEstimate(cid: Int): Estimate {
        return repo.getEstimate(cid)
    }

}