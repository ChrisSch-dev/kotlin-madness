package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.Todo
import com.example.todoapp.data.TodoDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = TodoDatabase.getDatabase(app).todoDao()
    val todos = dao.getAll().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addTodo(text: String) = viewModelScope.launch {
        dao.insert(Todo(text = text))
    }

    fun toggleTodo(todo: Todo) = viewModelScope.launch {
        dao.update(todo.copy(isDone = !todo.isDone))
    }

    fun deleteTodo(todo: Todo) = viewModelScope.launch {
        dao.delete(todo)
    }
}