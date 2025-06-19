package com.example.ktorclient.data

import retrofit2.http.GET

interface NotesApi {
    @GET("posts")
    suspend fun getNotes(): List<Note>
}