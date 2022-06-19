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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.borombo.hello.R
import com.borombo.hello.ui.theme.HelloTheme

@Composable
fun ListScreen(listViewModel: ListViewModel = ListViewModel()){

    // Convert the LiveData to a state to be able to update the view accordingly
    val list by listViewModel.list.observeAsState(listOf())

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        ListTitle()
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(list) { item ->
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
        text = stringResource(id = R.string.list_title),
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