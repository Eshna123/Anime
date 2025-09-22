package com.seekho.animeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import com.seekho.animeapp.ui.AppNavGraph
import com.seekho.animeapp.ui.theme.SeekhoTheme

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SeekhoTheme { AppNavGraph() }
        }
    }
}
