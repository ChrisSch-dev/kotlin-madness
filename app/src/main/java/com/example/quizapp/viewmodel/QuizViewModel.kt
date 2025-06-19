package com.example.quizapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.data.QuizDatabase
import com.example.quizapp.data.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class QuizViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = QuizDatabase.getDatabase(app, viewModelScope).questionDao()
    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions

    var currentIndex = 0
    var score = 0

    init {
        viewModelScope.launch {
            _questions.value = dao.getAll().first()
        }
    }

    fun answer(selected: Int) {
        val question = _questions.value[currentIndex]
        if (selected == question.answer) score++
        currentIndex++
    }
}