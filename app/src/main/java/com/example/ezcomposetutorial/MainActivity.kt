package com.example.ezcomposetutorial

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.Surface
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.ezcomposetutorial.ui.theme.EZComposeTutorialTheme
import java.security.AccessController.getContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EZComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    //Greeting(Message("Android", "Compose"))
                    Conversation(message = SampleData.conversationSample)
                }
            }
        }
    }
}

data class Message(var first: String, var second: String)

@Composable
fun Greeting(name: Message) {
    //??????????????????????????? remember ?????????????????????????????????????????????????????? mutableStateOf ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
    var isClicked by remember {
        mutableStateOf(false)
    }

    val surfaceColor by animateColorAsState(targetValue =
        if (isClicked) MaterialTheme.colors.secondary else MaterialTheme.colors.surface
    )
    Row(modifier = Modifier
        .padding(all = 16.dp)
        .clickable { isClicked = !isClicked }) {

        Image(painter = painterResource(id = R.drawable.app),
            contentDescription = "app",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .padding(0.dp, 0.dp, 4.dp, 0.dp)

            //.clickable { Toast.makeText(context, "abc", Toast.LENGTH_LONG).show() }
            //.border(0.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )

        // TODO: ?????????column????????????????????????text??????????????????
        Column(verticalArrangement = Arrangement.Center,
            //modifier = Modifier.fillMaxWidth().padding(24.dp)
        ) {
            Text(text = "Hello ${name.first}! Compose",
                style = MaterialTheme.typography.subtitle2
            )

            //Spacer(modifier = Modifier.height(2.dp))

            Surface(shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                    color = surfaceColor,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                ) {
                Text(text = "${name.second}",
                    modifier = Modifier.padding(0.dp, 4.dp, 0.dp ,0.dp),
                    fontSize = 10.sp,
                    maxLines = if (isClicked) 99 else 1,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
fun Conversation(message: List<Message>) {
    LazyColumn {
        message.map { item { Greeting(name = it) } }
    }
    /*for (name in message) {
        Greeting(name = name)
    }*/
}

@Preview(showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun DefaultPreview() {
    EZComposeTutorialTheme {
        Surface {
            //Greeting(Message("Android", "Compose"))
            Conversation(message = SampleData.conversationSample)
        }
    }
}