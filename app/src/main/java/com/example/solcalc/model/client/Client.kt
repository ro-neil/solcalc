package com.example.solcalc.model.client

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "client")
data class Client(
    @PrimaryKey(autoGenerate = true) var cid: Int,
    var name: String,
    var addr: String,
    var email: String)
