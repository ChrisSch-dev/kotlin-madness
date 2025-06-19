package com.example.ktorclient.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey val id: Int,
    val title: String,
    val body: String
)