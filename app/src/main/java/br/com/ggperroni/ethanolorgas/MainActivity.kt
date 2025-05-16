package br.com.ggperroni.ethanolorgas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.ggperroni.ethanolorgas.ui.theme.EthanolOrGasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EthanolOrGasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {

    var gasValue by remember {
        mutableStateOf("")
    }
    var ethanolValue by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Ethanol or Gas?",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            AnimatedVisibility(visible = gasValue.isNotBlank() && ethanolValue.isNotBlank()) {
                if (gasValue.isNotBlank() && ethanolValue.isNotBlank()) {
                    val pricePercentage = ethanolValue.toDouble() / gasValue.toDouble() < 0.7
                    Text(
                        text = if (pricePercentage) "Ethanol \uD83C\uDF3E" else "Gas ⛽",
                        style = TextStyle(
                            color = if (pricePercentage) Color.Green else Color.Red,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
            TextField(
                leadingIcon = {
                    Image(
                        painterResource(id = R.drawable.attach_money),
                        contentDescription = "attachMoney"
                    )
                },
                value = ethanolValue,
                onValueChange = {
                    ethanolValue = it
                },
                label = {
                    Text(text = "Ethanol Price")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            TextField(
                leadingIcon = {
                    Image(
                        painterResource(id = R.drawable.attach_money),
                        contentDescription = "attachMoney"
                    )
                },
                value = gasValue,
                onValueChange = { gasValue = it },
                label = {
                    Text(text = "Gas Price")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
        }
    }
}