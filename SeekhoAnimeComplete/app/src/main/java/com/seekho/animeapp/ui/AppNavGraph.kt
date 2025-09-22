package com.seekho.animeapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.seekho.animeapp.ui.screens.AnimeDetailScreen
import com.seekho.animeapp.ui.screens.AnimeListScreen
import com.seekho.animeapp.viewmodel.AnimeDetailViewModel
import com.seekho.animeapp.viewmodel.AnimeListViewModel

@Composable
fun AppNavGraph(modifier: Modifier = Modifier) {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = "list") {
        composable("list") {
            val vm: AnimeListViewModel = hiltViewModel()
            AnimeListScreen(vm) { id -> nav.navigate("detail/$id") }
        }
        composable("detail/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType })) { back ->
            val id = back.arguments?.getInt("id") ?: 0
            val vm: AnimeDetailViewModel = hiltViewModel()
            AnimeDetailScreen(vm, id)
        }
    }
}
