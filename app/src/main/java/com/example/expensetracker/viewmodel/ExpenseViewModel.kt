package com.example.expensetracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.Expense
import com.example.expensetracker.data.ExpenseDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ExpenseViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = ExpenseDatabase.getDatabase(app).expenseDao()
    val expenses = dao.getAll().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addExpense(name: String, amount: Double, category: String) = viewModelScope.launch {
        dao.insert(Expense(name = name, amount = amount, category = category))
    }

    fun deleteExpense(expense: Expense) = viewModelScope.launch {
        dao.delete(expense)
    }
}