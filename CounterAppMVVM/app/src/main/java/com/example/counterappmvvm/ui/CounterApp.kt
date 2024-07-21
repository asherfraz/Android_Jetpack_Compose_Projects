package com.example.counterappmvvm.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.counterappmvvm.Counter_ViewModel
import com.example.counterappmvvm.ui.theme.CounterAppMVVMTheme

@Composable
fun CounterApp(viewModel: Counter_ViewModel) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Counter: ${viewModel.counter.value}",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = {
                if (viewModel.counter.value == 0) {
                    Toast.makeText(context, "Counter has reach its minimum!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    viewModel.decrementCounter()
                }
            }) {
                Text(text = "Decrement")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                if (viewModel.counter.value == 100) {
                    Toast.makeText(context, "Counter has reach its maximum!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    viewModel.incrementCounter()
                }
            }) {
                Text(text = "Increment")
            }
        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CounterAppPreview() {
    CounterAppMVVMTheme {
        CounterApp(viewModel = Counter_ViewModel())
    }
}