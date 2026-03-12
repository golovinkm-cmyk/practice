package ci.nsu.moble.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ci.nsu.moble.main.ui.theme.PracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreenActivity(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreenActivity(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Поле ввода текста
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Введите данные для отправки") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                // Переход во вторую активити с данными
                val intent = Intent(context, SecondActivity::class.java).apply {
                    putExtra("text_data", text)
                }
                context.startActivity(intent)
            },
            modifier = Modifier.padding(top = 16.dp),
            enabled = text.isNotBlank() // Кнопка активна, если текст не пустой
        ) {
            Text("Open SecondActivity")
        }
    }
}
