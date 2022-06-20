package com.borombo.hello

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.borombo.hello.form.FormViewModel
import com.borombo.hello.form.FormulaFormScreen
import com.borombo.hello.list.ListScreen
import com.borombo.hello.ui.theme.HelloTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            HelloTheme {
                Scaffold { padding ->
                    NavigationComponent(navController, modifier = Modifier.padding(padding))
                }
            }
        }
    }
}

@Composable
fun NavigationComponent(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = "form",
        modifier = modifier
    ) {
        composable("form") {
            FormulaFormScreen(navController)
        }
        composable("list") {
            ListScreen()
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HelloTheme {
        FormulaFormScreen(rememberNavController())
    }
}