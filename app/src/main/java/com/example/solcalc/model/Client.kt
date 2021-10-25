package com.example.solcalc.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "client")
data class Client(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var name: String,
    var addr: String,
    var avgLB: Float,
    var perCut: Int,
    var batteries: Boolean)
