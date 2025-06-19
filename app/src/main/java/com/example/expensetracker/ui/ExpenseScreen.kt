package com.example.expensetracker.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.expensetracker.data.Expense
import com.example.expensetracker.viewmodel.ExpenseViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ExpenseScreen(viewModel: ExpenseViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    val expenses by viewModel.expenses.collectAsState()

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = amount, onValueChange = { amount = it }, label = { Text("Amount") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Category") }, modifier = Modifier.fillMaxWidth())
        Button(
            onClick = {
                val amt = amount.toDoubleOrNull()
                if (name.isNotBlank() && amt != null) {
                    viewModel.addExpense(name, amt, category)
                    name = ""; amount = ""; category = ""
                }
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) { Text("Add Expense") }
        Text("Total: \$${expenses.sumOf { it.amount }}", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(expenses.size) { i ->
                val expense = expenses[i]
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("${expense.name} (\$${expense.amount})")
                        if (expense.category.isNotBlank()) Text("Category: ${expense.category}", style = MaterialTheme.typography.bodySmall)
                    }
                    IconButton(onClick = { viewModel.deleteExpense(expense) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
        }
    }
}