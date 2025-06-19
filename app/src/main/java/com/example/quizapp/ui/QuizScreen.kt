package com.example.quizapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quizapp.viewmodel.QuizViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun QuizScreen(viewModel: QuizViewModel = viewModel()) {
    val questions by viewModel.questions.collectAsState()
    var finished by remember { mutableStateOf(false) }
    var lastScore by remember { mutableStateOf(0) }

    if (finished) {
        Column(Modifier.padding(16.dp)) {
            Text("Quiz Finished! Score: ${viewModel.score}/${questions.size}", style = MaterialTheme.typography.headlineMedium)
            Button(onClick = {
                finished = false
                viewModel.currentIndex = 0
                lastScore = viewModel.score
                viewModel.score = 0
            }) { Text("Restart") }
        }
    } else {
        if (questions.isNotEmpty() && viewModel.currentIndex < questions.size) {
            val q = questions[viewModel.currentIndex]
            Column(Modifier.padding(16.dp)) {
                Text(q.question, style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(8.dp))
                q.options.forEachIndexed { idx, opt ->
                    Button(
                        onClick = {
                            viewModel.answer(idx)
                            if (viewModel.currentIndex >= questions.size) finished = true
                        },
                        Modifier.fillMaxWidth().padding(bottom = 8.dp)
                    ) { Text(opt) }
                }
            }
        }
    }
}