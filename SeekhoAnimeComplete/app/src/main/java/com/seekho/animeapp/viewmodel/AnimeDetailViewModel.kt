package com.seekho.animeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seekho.animeapp.model.AnimeDetailResponse
import com.seekho.animeapp.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(private val repo: AnimeRepository): ViewModel() {
    private val _state = MutableStateFlow<UiState<AnimeDetailResponse>>(UiState.Loading)
    val state: StateFlow<UiState<AnimeDetailResponse>> = _state

    fun load(id: Int) = viewModelScope.launch {
        _state.value = UiState.Loading
        when(val res = repo.getAnimeDetail(id)) {
            is Result.Success -> _state.value = UiState.Success(res.getOrNull()!!)
            is Result.Failure -> _state.value = UiState.Error(res.exceptionOrNull()?.message)
        }
    }
}
