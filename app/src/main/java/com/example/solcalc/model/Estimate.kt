package com.example.solcalc.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import javax.xml.transform.OutputKeys

@Entity(tableName = "estimate")
data class Estimate(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ForeignKey(
        entity = Client::class,
        parentColumns = ["id"],
        childColumns = ["id"],
        onDelete = ForeignKey.CASCADE
    ) var clientID: Int,
    var noSP: Int,
    var inSize: Int,
    var storage: Int,
    var billCut: Float,
    var payback: Float,
    var totalCost: Float
)
