package com.test.mazetv.main.shows.viewModel

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
class ShowViewModel @Inject constructor(private val showRepository: ShowRepository) : ViewModel() {
  private val _showState = MutableStateFlow<UiState<List<Shows>>>(UiState.Loading)
  val showState: StateFlow<UiState<List<Shows>>> = _showState.asStateFlow()

  init {
    fetchMovies()
  }

  fun retry() {
    fetchMovies()
  }

  private fun fetchMovies() {
    viewModelScope.launch {
      _showState.value = UiState.Loading
      try {
        val shows = showRepository.getShows()

        _showState.value = UiState.Success(shows)
      } catch (e: Exception) {
        val errorMessage = e.message ?: "Something went Wrong"

        _showState.value = UiState.Error(errorMessage)
      }
    }
  }
}
