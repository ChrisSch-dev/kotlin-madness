package com.example.weatherapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.data.WeatherResponse

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    var city by remember { mutableStateOf("") }
    var apiKey by remember { mutableStateOf("") } // let user enter for demo, replace with your key for prod

    val weather by viewModel.weather.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = city,
            label = { Text("City") },
            onValueChange = { city = it },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = apiKey,
            label = { Text("API Key (OpenWeatherMap)") },
            onValueChange = { apiKey = it },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { viewModel.fetchWeather(city, apiKey) },
            enabled = !loading && city.isNotBlank() && apiKey.isNotBlank()
        ) {
            Text("Get Weather")
        }
        Spacer(Modifier.height(16.dp))
        when {
            loading -> CircularProgressIndicator()
            error != null -> Text(error ?: "", color = MaterialTheme.colorScheme.error)
            weather != null -> WeatherResult(weather!!)
        }
    }
}

@Composable
fun WeatherResult(data: WeatherResponse) {
    Column {
        Text("Weather in ${data.name}", style = MaterialTheme.typography.headlineSmall)
        Text("${data.main.temp}°C")
        Row {
            if (data.weather.isNotEmpty()) {
                val iconCode = data.weather[0].icon
                Image(
                    painter = rememberAsyncImagePainter("https://openweathermap.org/img/wn/${iconCode}@2x.png"),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(data.weather[0].description)
            }
        }
    }
}