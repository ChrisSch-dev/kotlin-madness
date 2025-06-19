package com.example.ktorclient.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktorclient.data.NoteDatabase
import com.example.ktorclient.data.NotesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotesViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = NotesRepository(NoteDatabase.getDatabase(app).noteDao())
    val notes = repo.notes().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    var error: String? = null

    fun refresh() = viewModelScope.launch {
        try {
            repo.refreshNotes()
            error = null
        } catch (e: Exception) {
            error = e.message
        }
    }
}