package com.borombo.hello

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.borombo.hello.form.FormViewModel
import com.borombo.hello.form.FormulaFormScreen
import com.borombo.hello.list.ListScreen
import com.borombo.hello.ui.theme.HelloTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            HelloTheme {
                Scaffold {
                    NavigationComponent(navController)
                }
            }
        }
    }
}

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "form"
    ) {
        composable("form") {
            FormulaFormScreen(navController, FormViewModel())
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