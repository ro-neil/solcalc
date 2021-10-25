package com.example.solcalc.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface EstimateDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(estimate: Estimate)
}