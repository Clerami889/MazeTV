package com.test.mazetv.shows.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.mazetv.data.models.Shows
import com.test.mazetv.repository.ShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowViewModel @Inject constructor(private val showRepository: ShowRepository) : ViewModel() {
  private val _show = MutableStateFlow<List<Shows>>(emptyList())

  val show: StateFlow<List<Shows>>
    get() = _show

  init {
    fetchMovies()
  }

  private fun fetchMovies() {
    viewModelScope.launch {
      _show.value = showRepository.getShows()
    }
  }
}
