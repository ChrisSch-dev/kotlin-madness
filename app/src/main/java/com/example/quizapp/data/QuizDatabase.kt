package com.example.quizapp.data

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Question::class], version = 1)
@TypeConverters(Converters::class)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao

    companion object {
        @Volatile private var INSTANCE: QuizDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): QuizDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java,
                    "quiz_db"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        scope.launch {
                            getDatabase(context, scope).questionDao().insertAll(
                                listOf(
                                    Question(question = "Capital of France?", options = listOf("Paris", "London", "Berlin"), answer = 0),
                                    Question(question = "2+2?", options = listOf("3", "4", "5"), answer = 1)
                                )
                            )
                        }
                    }
                }).build().also { INSTANCE = it }
            }
    }
}