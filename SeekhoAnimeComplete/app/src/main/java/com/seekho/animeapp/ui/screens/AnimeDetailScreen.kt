package com.seekho.animeapp.ui.screens

import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.seekho.animeapp.viewmodel.AnimeDetailViewModel
import com.seekho.animeapp.viewmodel.UiState

@Composable
fun AnimeDetailScreen(vm: AnimeDetailViewModel, id: Int) {
    val ctx = LocalContext.current
    val state by vm.state.collectAsState()
    LaunchedEffect(id) { vm.load(id) }

    Scaffold(topBar = { TopAppBar(title = { Text("Detail") }) }) { p->
        Box(modifier = Modifier.fillMaxSize().padding(p)) {
            when(state) {
                is UiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                is UiState.Success -> {
                    val data = (state as UiState.Success).data.data
                    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                        val yt = data.trailer?.youtube_id
                        if (!yt.isNullOrEmpty()) {
                            Button(onClick = { val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$yt")); ctx.startActivity(i) }) { Text("Play Trailer") }
                        } else {
                            AsyncImage(model = data.images?.values?.firstOrNull()?.image_url, contentDescription = data.title, modifier = Modifier.fillMaxWidth().height(220.dp))
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(data.title ?: "-", style = MaterialTheme.typography.h6)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Episodes: ${'$'}{data.episodes ?: "?"}")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Score: ${'$'}{data.score ?: "?"}")
                        Spacer(modifier = Modifier.height(12.dp))
                        Text("Genres: ${'$'}{data.genres?.joinToString { it.name ?: "" } ?: "N/A"}")
                        Spacer(modifier = Modifier.height(12.dp))
                        Text("Synopsis:")
                        Text(data.synopsis ?: "N/A")
                        Spacer(modifier = Modifier.height(12.dp))
                        Text("Main Cast:")
                        Text(data.characters?.data?.take(5)?.joinToString { it.character?.name ?: "" } ?: "N/A")
                    }
                }
                is UiState.Error -> Text((state as UiState.Error).message ?: "Error", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}
