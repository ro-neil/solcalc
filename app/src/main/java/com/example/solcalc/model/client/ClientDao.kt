package com.example.solcalc.model.client

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.solcalc.model.client.Client

@Dao
interface ClientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClient(client: Client)

    @Query("SELECT * FROM Client ORDER BY cid DESC LIMIT 3")
    fun getLastThreeEntries(): LiveData<List<Client>>

    @Query("SELECT * FROM Client JOIN Estimate ON Client.cid = Estimate.clientID WHERE cid = :sid")
    fun getClientEstimate(sid:Int): LiveData<List<Client>>

    @Query("DELETE FROM Client")
    suspend fun deleteAll()

    @Query("SELECT * FROM Client")
    fun getAllClients(): LiveData<List<Client>>

    @Query("SELECT * FROM Client ORDER BY cid DESC LIMIT 1")
    fun getLastClient(): List<Client>


    @Query("SELECT * FROM Client WHERE cid = :cid")
    fun getSpecificClient(cid:Int): Client
}