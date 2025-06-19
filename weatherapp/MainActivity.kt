package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var city by remember { mutableStateOf("") }
            var weather by remember { mutableStateOf("Enter a city and press Get Weather") }
            Column(Modifier.padding(16.dp)) {
                OutlinedTextField(value = city, onValueChange = { city = it }, label = { Text("City") })
                Button(onClick = { weather = "Current weather in $city: 25°C (mocked)" }) {
                    Text("Get Weather")
                }
                Spacer(Modifier.height(16.dp))
                Text(weather)
            }
        }
    }
}