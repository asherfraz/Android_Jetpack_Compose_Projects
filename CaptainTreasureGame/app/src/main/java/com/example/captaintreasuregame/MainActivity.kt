package com.example.captaintreasuregame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.captaintreasuregame.ui.theme.CaptainTreasureGameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaptainTreasureGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    CaptainTreasureGame()
                }
            }
        }
    }

    @Composable
    fun CaptainTreasureGame() {
        val treasureFound = remember {
            mutableStateOf(0)
        }
        val direction = remember {
            mutableStateOf("North")
        }
        val place = remember {
            mutableStateOf("Search for Treasure . . . ")
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(text = "Treasures Found: ${treasureFound.value}")
            Text(text = "Current Direction: ${direction.value}")

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        direction.value = "North"
                        if (Random.nextBoolean()) {
                            treasureFound.value++
                            place.value = "Treasure Found! you're on the way"
                        } else {
                            place.value = "Aww, you get lost! keep digging . . ."
                        }
                    },
                ) {
                    Text(text = "Sail North")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                Button(onClick = {
                    direction.value = "West"
                    if (Random.nextBoolean()) {
                        treasureFound.value++
                        place.value = "Treasure Found! you're on the way"
                    } else {
                        place.value = "Aww, you get lost! keep digging . . ."
                    }
                }) {
                    Text(text = "Sail West")
                }
                Button(onClick = {
                    direction.value = "East"
                    if (Random.nextBoolean()) {
                        treasureFound.value++
                        place.value = "Treasure Found! you're on the way"
                    } else {
                        place.value = "Aww, you get lost! keep digging . . ."
                    }
                }) {
                    Text(text = "Sail East")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        direction.value = "South"
                        if (Random.nextBoolean()) {
                            treasureFound.value++
                            place.value = "Treasure Found! you're on the way"
                        } else {
                            place.value = "Aww, you get lost! keep digging . . ."
                        }
                    },
                ) {
                    Text(text = "Sail South")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = place.value)
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun CaptainTreasureGamePreview() {
        CaptainTreasureGameTheme {
            CaptainTreasureGame()
        }
    }
}



