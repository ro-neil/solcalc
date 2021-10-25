package com.example.solcalc.model

class EstimateRepo (private val estimateDao: EstimateDao){

    suspend fun insert(estimate: Estimate){
        estimateDao.insert(estimate)
    }
}