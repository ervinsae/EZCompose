package com.example.ezcomposetutorial.basicLayoutsCodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ezcomposetutorial.ui.theme.EZComposeTutorialTheme

/**
 *
 * Create by Ervin on 2023/9/19
 **/
class TitleBarActivity: ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EZComposeTutorialTheme {
                MyPage(modifier = Modifier.wrapContentSize(), title = "Spotify")
                //SpotifyTitle(text = "Spotify")
            }
        }
    }

    @Composable
    fun SpotifyTitle(text: String, modifier: Modifier = Modifier) {
        //Scaffold() {
            TopAppBar() {
                Text(
                    text = text,
                    color = Color.White,
                    style = typography.h5.copy(fontWeight = FontWeight.ExtraBold),
                    modifier = modifier.padding(start = 24.dp, end = 4.dp, bottom = 8.dp, top = 8.dp)
                )
            }
        //}

    }

    @Composable
    fun MyPage(modifier: Modifier = Modifier, title: String) {

        var flag by remember { mutableStateOf(true) }

        /*Column {
            SpotifyTitle(text = title, modifier = Modifier.wrapContentHeight())
            if (flag) {
                OnShowScreen(onButtonClicked = { flag = false })
            } else {
                finish()
            }
        }*/

        Scaffold {
            Column() {
                SpotifyTitle(text = title)
                if (flag) {
                    OnShowScreen(onButtonClicked = { flag = false })
                } else {
                    /*flag = true
                    Toast.makeText(this, "finish", Toast.LENGTH_SHORT).show()*/
                    Greetings()
                }
            }

        }
    }

    @Composable
    fun OnShowScreen(
        onButtonClicked: () -> Unit,
        modifier: Modifier = Modifier) {

        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "This is a title bar demo page",
            )
            Button(
                onClick = onButtonClicked,
                modifier = Modifier.padding(vertical = 24.dp)) {
                Text(text = "Click")
            }
        }
    }

    @Composable
    fun Greetings(modifier: Modifier = Modifier, names: List<String> = listOf("Hello", "World")) {
        Column(modifier = modifier
            .padding(vertical = 4.dp)
            .fillMaxSize()) {
            for (name in names) {
                GreetingCard(name = name)
            }
        }

    }

    @Composable
    private fun GreetingCard(name: String) {
        val expanded = remember { mutableStateOf(false) }

        Surface(
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Row(modifier = Modifier.padding(24.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Hello, ")
                    Text(text = name)
                }
                Button(
                    modifier = Modifier
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                        .clip(RoundedCornerShape(20.dp)),

                    onClick = { expanded.value = !expanded.value }
                ) {
                    Text(if (expanded.value) "Show less" else "Show more")
                }
            }
        }
    }
}