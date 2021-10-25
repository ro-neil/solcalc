package com.example.solcalc.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ClientDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(client:Client)

    @Query("SELECT name,addr FROM Client ORDER BY id DESC LIMIT 3")
    fun getLastThreeEntries(): LiveData<List<Client>>

    @Query("SELECT name,addr FROM Client JOIN Estimate ON Client.id = Estimate.clientID WHERE id = :sid")
    fun getClientEstimate(sid:Int): Client
}