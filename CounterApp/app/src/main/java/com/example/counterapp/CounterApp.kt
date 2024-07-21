package com.example.counterapp

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun CounterApp(viewModel: CounterViewModel) {
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {
        Text(
            text = "Count: ${viewModel.count.value}",
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Button(onClick = {
                if (viewModel.count.value == 0) {
                    Toast.makeText(context, "Counter already at Zero!", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.decrement()
                }
            }) {
                Text(text = "Decrement")
            }

            Button(onClick = {
                viewModel.increment()
            }) {
                Text(text = "Increment")
            }

        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CounterAppPreview() {
//    CounterApp()
}