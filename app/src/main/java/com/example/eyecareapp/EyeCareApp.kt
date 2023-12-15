package com.example.eyecareapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.eyecareapp.ui.components.navigation.Screen
import com.example.eyecareapp.ui.components.navigation.bottomBar.BottomBar
import com.example.eyecareapp.ui.screen.Cart.CartScreen
import com.example.eyecareapp.ui.screen.Detail.DetailScreen
import com.example.eyecareapp.ui.screen.Detail.Payment.PaymentScreen
import com.example.eyecareapp.ui.screen.Home.HomeScreen
import com.example.eyecareapp.ui.screen.Login.LoginScreen
import com.example.eyecareapp.ui.screen.Profile.ChangeProfileScreen
import com.example.eyecareapp.ui.screen.Profile.ProfileScreen
import com.example.eyecareapp.ui.screen.Register.RegisterScreen
import com.example.eyecareapp.ui.screen.Test.ColourBlind.ColourBlindScreen
import com.example.eyecareapp.ui.screen.Test.Hypomia.HypomiaScreen
import com.example.eyecareapp.ui.screen.Test.Result.ResultScreen
import com.example.eyecareapp.ui.screen.Test.TestScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EyeCareApp (
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
){
    val navController = rememberNavController()
    var showBottomBar by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    showBottomBar = when (navBackStackEntry?.destination?.route) {
        "Home/{id}" -> false
        "Cart/Payment"-> false
        "Profile/Change" -> false
        "Test/BlindColour" -> false
        "Test/Hypomia" -> false
        else -> true
    }
    Scaffold (
            bottomBar = {
                    if(showBottomBar){
                        BottomBar(navController = navController)
                    }
            },
            modifier = modifier
    ){
        innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.login.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.login.route){
                LoginScreen(
                    navigateToRegister = {
                        navController.navigate(Screen.register.route)
                    }
                )
            }
            composable(Screen.register.route){
                RegisterScreen(
                    navigateToLogin = {
                        navController.navigate(Screen.login.route)
                    }
                )
            }
            composable(Screen.home.route){
                HomeScreen(
                    navigateToDetail = {
                        id -> navController.navigate(Screen.detail.createRoute(id))
                    }
                )
            }
            composable(Screen.test.route){
                TestScreen(
                        navigateToHypomiaTest = {
                            navController.navigate(Screen.hypomia.route)
                        },
                        navigateToColourBlindTest = {
                            navController.navigate(Screen.blindcolour.route)
                        }
                )
            }
            composable(Screen.hypomia.route){
                HypomiaScreen(
                    navigateToResult = {
                        navController.navigate(Screen.result.route)
                    }
                )
            }
            composable(Screen.blindcolour.route){
                ColourBlindScreen(
                    navigateToResult = {
                        navController.navigate(Screen.result.route)
                    }
                )
            }
            composable(Screen.result.route){
                ResultScreen(
                    navigateToDetail = {
                        navController.navigate(Screen.detail.route)
                    }
                )
            }
            composable(
                route = Screen.detail.route,
                arguments = listOf(navArgument("id"){
                    type = NavType.IntType
                })
                ){
                val id = it.arguments?.getInt("id")?:-1L
                DetailScreen( id = id as Int,
                    navigateToPayment = {
                        navController.navigate(Screen.payment.route)
                    },
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable(Screen.profile.route){
                ProfileScreen(
                    navigateToChangeProfile = {
                        navController.navigate(Screen.changeprofile.route)
                    }
                )
            }
            composable(Screen.cart.route){
                CartScreen(
                    navigateToDetail = {
                            id -> navController.navigate(Screen.detail.createRoute(id))
                    }
                )
            }
            composable(Screen.payment.route){
                PaymentScreen()
            }
            composable(Screen.changeprofile.route){
                ChangeProfileScreen(
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }

}