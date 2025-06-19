package com.example.ktorclient.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NotesRepository(private val dao: NoteDao) {
    private val api: NotesApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(NotesApi::class.java)
    }

    suspend fun refreshNotes() {
        val notes = api.getNotes()
        dao.clearAll()
        dao.insertAll(notes)
    }

    fun notes() = dao.getAll()
}