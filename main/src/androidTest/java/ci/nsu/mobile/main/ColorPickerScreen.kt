package ci.nsu.mobile.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPickerScreen(
    viewModel: ColorPickerViewModel = viewModel(),
    it: Float
) {
    // Подписка на состояние ViewModel
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Заголовок
            Text(
                text = "Color Picker",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            // Карточка предпросмотра цвета
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(uiState.color)
                        .clip(RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.hexCode,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = uiState.textColor
                    )
                }
            }

            // Красный слайдер
            ColorSlider(
                label = "Red",
                value = uiState.red.toFloat(),
                onValueChange = { viewModel.onRedChanged(it) },
                colorRange = Color.Red
            )

            // Зеленый слайдер
            ColorSlider(
                label = "Green",
                value = uiState.green.toFloat(),
                onValueChange = { viewModel.onGreenChanged(it) },
                colorRange = Color.Green
            )

            // Синий слайдер
            ColorSlider(
                label = "Blue",
                value = uiState.blue.toFloat(),
                onValueChange = {
                    val it = 0.0f
                    viewModel.onBlueChanged(it)
                },
                colorRange = Color.Blue
            )

            // Кнопка случайного цвета
            Button(
                onClick = { viewModel.generateRandomColor() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "🎲 Random Color",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Информационная карточка с RGB значениями
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "RGB Values",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "🔴 R: ${uiState.red}  |  🟢 G: ${uiState.green}  |  🔵 B: ${uiState.blue}",
                        fontSize = 14.sp
                    )
                    Text(
                        text = "HEX: ${uiState.hexCode}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )

                }
            }
        }
    }


    // Компонент слайдера для выбора цвета
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ColorSlider(
        label: String,
        value: Float,
        onValueChange: (Float) -> Unit,
        colorRange: Color
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = label,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = colorRange
                )
                Text(
                    text = "${value.toInt()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = colorRange
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Slider(
                value = value,
                onValueChange = onValueChange,
                valueRange = 0f..255f,
                colors = SliderDefaults.colors(
                    thumbColor = colorRange,
                    activeTrackColor = colorRange,
                    inactiveTrackColor = colorRange.copy(alpha = 0.3f)
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }


}

@Composable
fun ColorSlider(label: String, value: Float, onValueChange: () -> Unit, colorRange: Color) {
    TODO("Not yet implemented")
}