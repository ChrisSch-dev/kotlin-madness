package com.example.quizapp.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromList(list: List<String>): String = list.joinToString("|")
    @TypeConverter
    fun toList(str: String): List<String> = str.split("|")
}