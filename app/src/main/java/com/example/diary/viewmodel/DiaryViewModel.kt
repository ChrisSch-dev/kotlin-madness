package com.example.diary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.diary.data.DiaryDatabase
import com.example.diary.data.DiaryEntry
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DiaryViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = DiaryDatabase.getDatabase(app).diaryDao()
    val entries = dao.getAll().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addEntry(content: String) = viewModelScope.launch {
        dao.insert(DiaryEntry(content = content))
    }
}