package com.example.solcalc.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.solcalc.model.client.Client

class EstimateViewModel(application: Application):AndroidViewModel(application) {
   private val repo:SolCalcRepo
   val lastThreeClients: LiveData<List<Client>>

   init{
       val db = SolCalcDatabase.getDatabase(application, viewModelScope)
       val clientDao = db.clientDao()
       val estimateDao = db.estimateDao()

       repo = SolCalcRepo(clientDao, estimateDao)
       lastThreeClients = repo.client

   }

}