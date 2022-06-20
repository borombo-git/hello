package com.borombo.hello.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.borombo.hello.R
import com.borombo.hello.ui.theme.HelloTheme

@Composable
fun ListScreen(listViewModel: ListViewModel = hiltViewModel()){

    val listState = rememberLazyListState()

    val firstItem by rememberSaveable { derivedStateOf { listState.firstVisibleItemIndex } }

    if (firstItem > listViewModel.loadMoreThreshold){
        listViewModel.loadMore()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        ListTitle()
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            items(listViewModel.valueList) { item ->
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