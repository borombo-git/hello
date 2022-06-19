package com.borombo.hello.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.borombo.hello.ui.theme.HelloTheme

@Composable
fun ListScreen(listViewModel: ListViewModel = ListViewModel()){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        ListTitle()
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(listViewModel.list) { item ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = item,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )

                }
            }
        }
    }
}

@Composable
fun ListTitle(){
    Text(
        text = "Generated List",
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview() {
    HelloTheme {
        ListScreen()
    }
}