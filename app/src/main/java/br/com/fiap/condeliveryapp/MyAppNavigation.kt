package br.com.fiap.condeliveryapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.condeliveryapp.pages.CadastrarPage
import br.com.fiap.condeliveryapp.pages.HomePage
import br.com.fiap.condeliveryapp.pages.LoginPage

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier,authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login", builder={
        composable("login"){
            LoginPage(modifier, navController,authViewModel)
        }
        composable("cadastrar"){
            CadastrarPage(modifier, navController,authViewModel)
        }
        composable("home"){
            HomePage(modifier, navController,authViewModel)
        }
    })
}

