package com.example.testjetpackcompose
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CalculatorScreen()
            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    val onCalculate: (String) -> Unit = { operation ->
        val num1 = number1.toFloatOrNull()
        val num2 = number2.toFloatOrNull()

        result = if (num1 != null && num2 != null) {
            when (operation) {
                "+" -> (num1 + num2).toString()
                "-" -> (num1 - num2).toString()
                "*" -> (num1 * num2).toString()
                "/" -> if (num2 != 0f) (num1 / num2).toString() else "Cannot divide by 0"
                else -> "Invalid operation"
            }
        } else {
            "Invalid input"
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        BasicTextField(
            value = number1,
            onValueChange = { number1 = it },
            textStyle = TextStyle(fontSize = 24.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            decorationBox = { innerTextField ->
                if (number1.isEmpty()) {
                    Text("Enter number 1", fontSize = 24.sp)
                }
                innerTextField()
            }
        )


        BasicTextField(
            value = number2,
            onValueChange = { number2 = it },
            textStyle = TextStyle(fontSize = 24.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            decorationBox = { innerTextField ->
                if (number2.isEmpty()) {
                    Text("Enter number 2", fontSize = 24.sp)
                }
                innerTextField()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))


        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { onCalculate("+") }) {
                Text(text = "+")
            }
            Button(onClick = { onCalculate("-") }) {
                Text(text = "-")
            }
            Button(onClick = { onCalculate("*") }) {
                Text(text = "*")
            }
            Button(onClick = { onCalculate("/") }) {
                Text(text = "/")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Result: $result",
            fontSize = 30.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        CalculatorScreen()
    }
}
