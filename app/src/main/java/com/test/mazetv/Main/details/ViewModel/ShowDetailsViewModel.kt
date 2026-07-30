package com.test.mazetv.Main.details.ViewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.mazetv.core.UiState
import com.test.mazetv.data.models.Shows
import com.test.mazetv.repository.ShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailsViewModel
@Inject
constructor(
    private val showRepository: ShowRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
  private val _showState = MutableStateFlow<UiState<Shows>>(UiState.Loading)
  val showState: StateFlow<UiState<Shows>> = _showState.asStateFlow()

  private val movieId: Int = checkNotNull(savedStateHandle["showsId"])

  init {
    fetchMovieDetails()
  }

  private fun fetchMovieDetails() {
    viewModelScope.launch {
      _showState.value = UiState.Loading
      try {
        val movie = showRepository.getShowsById(movieId)
        _showState.value = UiState.Success(movie)
      } catch (e: Exception) {
        _showState.value = UiState.Error(e.message ?: "Unknown Error")
      }
    }
  }
}
