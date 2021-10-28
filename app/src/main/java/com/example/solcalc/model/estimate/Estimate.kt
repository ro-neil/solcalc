package com.example.solcalc.model.estimate

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.solcalc.model.client.Client

@Entity(tableName = "estimate", foreignKeys = [
    ForeignKey(entity = Client::class,
        parentColumns = ["cid"],
        childColumns = ["clientID"],
        onDelete = ForeignKey.CASCADE)])
data class Estimate(
    @PrimaryKey(autoGenerate = true) var eid: Int,
    var clientID: Int,
    var noSP: Int,
    var inSize: Int,
    var storage: Int,
    var billCut: Float,
    var payback: Float,
    var totalCost: Float
)
