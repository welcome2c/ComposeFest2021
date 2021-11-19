package kr.co.dhkim.layoutscompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch
import kr.co.dhkim.layoutscompose.ui.theme.LayoutsComposeTheme

@Composable
private fun SimpleList() {
    val scrollState = rememberScrollState()

    Scaffold {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            repeat(100) {
                Text("Item $it")
            }
        }
    }
}

@Composable
private fun ScrollingList() {
    val listSize = 100
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(0)
                }
            }, modifier = Modifier.weight(1f)) {
                Text("Scroll to the top")
            }

            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(listSize - 1)
                }
            }, modifier = Modifier.weight(1f)) {
                Text("Scroll to the end")
            }
        }

        LazyColumn(state = scrollState) {
            items(100) {
                ImageListItem(index = it)
            }
        }
    }
}

@Composable
private fun ImageListItem(index: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text(
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.weight(1f),
            text = "Item #$index"
        )
    }
}

@Composable
private fun PhotographerCardPreview() {
    LayoutsComposeTheme {
        ScrollingList()
    }
}