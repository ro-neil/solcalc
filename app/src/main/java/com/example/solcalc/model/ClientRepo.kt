package com.example.solcalc.model

class ClientRepo (private val clientDao: ClientDao) {

    suspend fun insert(client:Client){
        clientDao.insert(client)
    }

}