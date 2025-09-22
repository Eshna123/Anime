package com.seekho.animeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seekho.animeapp.db.AnimeEntity
import com.seekho.animeapp.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UiState<out T> {
    object Loading: UiState<Nothing>()
    data class Success<T>(val data: T): UiState<T>()
    data class Error(val message: String?): UiState<Nothing>()
}

@HiltViewModel
class AnimeListViewModel @Inject constructor(private val repo: AnimeRepository): ViewModel() {
    private val _state = MutableStateFlow<UiState<List<AnimeEntity>>>(UiState.Loading)
    val state: StateFlow<UiState<List<AnimeEntity>>> = _state

    init { load() }

    fun load() = viewModelScope.launch {
        _state.value = UiState.Loading
        when(val res = repo.refreshTopAnime()) {
            is Result.Success -> _state.value = UiState.Success(res.getOrNull()?: emptyList())
            is Result.Failure -> {
                val fallback = repo.getTopAnime()
                if (fallback.isSuccess) _state.value = UiState.Success(fallback.getOrNull()?: emptyList())
                else _state.value = UiState.Error(fallback.exceptionOrNull()?.message)
            }
        }
    }
}
