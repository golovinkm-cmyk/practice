package com.example.colorpalette.model
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColorPickerScreen()
        }
    }
}

@Composable
fun ColorPickerScreen() {

    val colorMap = mapOf(
        "Red" to Color.Red,
        "Orange" to Color(0xFFFFA500),
        "Yellow" to Color.Yellow,
        "Green" to Color.Green,
        "Blue" to Color.Blue,
        "Indigo" to Color(0xFF4B0082),
        "Violet" to Color(0xFF8F00FF)
    )


    var textFieldValue by remember { mutableStateOf("") }
    var buttonBackgroundColor by remember { mutableStateOf(Color.Green) } // Начальный цвет

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        TextField(
            value = textFieldValue,
            onValueChange = { textFieldValue = it },
            label = { Text("Введите название цвета") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )


        Button(
            onClick = {
                val foundColor = colorMap.entries.find{it.key.equals(textFieldValue.trim(),ignoreCase = true)}?.value
                if (foundColor != null) {
                    buttonBackgroundColor = foundColor
                } else {
                    Log.d("ColorPicker", "Пользовательский цвет \"$textFieldValue\" не найден")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = buttonBackgroundColor),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Применить цвет", color = Color.White, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))


        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(colorMap.toList()) { (name, color) ->
                ColorItem(name = name, color = color)
            }
        }
    }
}

@Composable
fun ColorItem(name: String, color: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color, shape = RoundedCornerShape(8.dp))
            .padding(start = 16.dp),
        contentAlignment = androidx.compose.ui.Alignment.CenterStart
    ) {
        Text(
            text = name,
            color = if (color == Color.Yellow || color == Color.White) Color.Black else Color.White,
            fontSize = 18.sp
        )
    }
}