package com.example.solcalc.model.estimate

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.solcalc.model.estimate.Estimate

@Dao
interface EstimateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEstimate(estimate: Estimate)

    @Query("DELETE FROM estimate")
    suspend fun deleteAll()

    @Query("SELECT * FROM estimate WHERE clientID = :cid")
    fun getEstimate(cid: Int): Estimate
}