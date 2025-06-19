package com.example.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Expense(val name: String, val amount: Double)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var name by remember { mutableStateOf("") }
            var amount by remember { mutableStateOf("") }
            var expenses by remember { mutableStateOf(listOf<Expense>()) }
            Column(Modifier.padding(16.dp)) {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                OutlinedTextField(value = amount, onValueChange = { amount = it }, label = { Text("Amount") })
                Button(onClick = {
                    val amt = amount.toDoubleOrNull()
                    if (name.isNotBlank() && amt != null) {
                        expenses = expenses + Expense(name, amt)
                        name = ""; amount = ""
                    }
                }) { Text("Add Expense") }
                Text("Total: \$${expenses.sumOf { it.amount }}")
                Spacer(Modifier.height(16.dp))
                LazyColumn {
                    items(expenses.size) { i ->
                        Row {
                            Text("${expenses[i].name}: \$${expenses[i].amount}")
                        }
                    }
                }
            }
        }
    }
}