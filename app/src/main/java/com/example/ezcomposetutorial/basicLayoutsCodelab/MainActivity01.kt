package com.example.ezcomposetutorial.basicLayoutsCodelab

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ezcomposetutorial.DefaultPreview
import com.example.ezcomposetutorial.R
import com.example.ezcomposetutorial.ui.theme.EZComposeTutorialTheme

/**
 *
 * Create by Ervin on 2022/12/28
 **/
class MainActivity01 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EZComposeTutorialTheme() {
                Surface(modifier = Modifier.wrapContentHeight(),
                    color = MaterialTheme.colors.surface) {
                    Column() {
                        SearchBar()
                        DefaultPreview()
                    }

                }
            }
        }
    }

    @Composable
    fun SearchBar(
        modifier: Modifier = Modifier,
    ) {
        //在activity中获取context
        val context = LocalContext.current
        var text by remember {
            mutableStateOf("")
        }
        TextField(
            value = text,
            onValueChange = {
                text = it
                if (it == "aaa") {

                    val intent = Intent(context, TitleBarActivity::class.java)
                    context.startActivity(intent)
                }
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "")
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface
            ),
            placeholder = {
                Text(text = "Search")          
            },
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp),
        )
    }

    @Composable
    fun AlignYourBodyElement(
        modifier: Modifier = Modifier,
        @DrawableRes drawableRes: Int,
        title: String

    ) {
        Column(
            //元素水平居中
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Image(
                painter = painterResource(drawableRes),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape),
                contentDescription = null
            )
            Text(text = title,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.paddingFromBaseline(
                    top = 24.dp, bottom = 8.dp
                ))
        }
    }
    
    @Composable
    fun FavoriteCollectionCard(modifier: Modifier = Modifier,
        @DrawableRes drawableRes: Int,
        title: String) {
        
        Surface(
            shape = MaterialTheme.shapes.small,
            modifier = modifier
        ) {
            
            Row (modifier = Modifier.size(192.dp),
                verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(id = drawableRes),
                    contentDescription = "meditations",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(56.dp))

                Text(modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp),
                    text = title,
                    style = MaterialTheme.typography.h3)
            }

        }
    }

    @Composable
    fun AlignYourBodyRow(modifier: Modifier = Modifier, onClick: (DrawableStringPair) -> Unit) {
        //modifier的配置也可以写在AlignYourBodyRow调用处(尝试了下，不行，没有效果？)
        LazyRow(modifier = modifier.padding(0.dp,16.dp,0.dp,0.dp).fillMaxHeight(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(alignYourBodyData) {
                //item的click事件
                item ->  Row(modifier = Modifier.clickable { onClick(item) }) {
                    AlignYourBodyElement(drawableRes = item.drawable, title = item.text)
                }
            }
        }
    }

    data class DrawableStringPair(
        @DrawableRes val drawable: Int,
        val text: String
    )

    private val alignYourBodyData = listOf(
        R.drawable.ab1_inversions to "inversions",
        R.drawable.ab2_quick_yoga to "quick_yoga",
        R.drawable.ab3_stretching to "stretching",
        R.drawable.ab4_tabata to "tabata",
        R.drawable.ab5_hiit to "hiit",
        R.drawable.ab6_pre_natal_yoga to "natal_yoga"
    ).map { DrawableStringPair(it.first, it.second) }

    @Preview(showBackground = true, widthDp = 320, backgroundColor = 0xFFF0EAE2)
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun DefaultPreview() {


        EZComposeTutorialTheme {
            AlignYourBodyRow(modifier = Modifier.padding(8.dp), onClick = {
                this.startActivity(Intent(this, TitleBarActivity::class.java))
            })

            //AlignYourBodyElement(modifier = Modifier.padding(8.dp), R.drawable.ab1_inversions, "Inversion")

            //FavoriteCollectionCard(modifier = Modifier.padding(8.dp), R.drawable.fc2_nature_meditations, "NM")


        }
    }
}