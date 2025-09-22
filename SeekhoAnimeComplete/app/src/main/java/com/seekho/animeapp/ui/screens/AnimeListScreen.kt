package com.seekho.animeapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.seekho.animeapp.db.AnimeEntity
import com.seekho.animeapp.viewmodel.UiState
import com.seekho.animeapp.viewmodel.AnimeListViewModel

@Composable
fun AnimeListScreen(vm: AnimeListViewModel, onItemClick: (Int)->Unit) {
    val state by vm.state.collectAsState()
    Scaffold(topBar = { TopAppBar(title = { Text("Top Anime") }) }) { p->
        Box(modifier = Modifier.fillMaxSize().padding(p)) {
            when(state) {
                is UiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                is UiState.Success -> {
                    val list = (state as UiState.Success<List<AnimeEntity>>).data
                    LazyColumn { items(list) { item -> AnimeRow(item){ onItemClick(item.id) } } }
                }
                is UiState.Error -> Text((state as UiState.Error).message ?: "Error", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun AnimeRow(item: AnimeEntity, onClick: ()->Unit) {
    Card(modifier = Modifier.padding(8.dp).fillMaxWidth().clickable { onClick() }, elevation = 4.dp) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(model = item.imageUrl, contentDescription = item.title, modifier = Modifier.size(100.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Column { Text(item.title ?: "-" ); Spacer(modifier = Modifier.height(6.dp)); Text("Episodes: ${'$'}{item.episodes ?: "?"}"); Text("Score: ${'$'}{item.score ?: "?"}") }
        }
    }
}
