package com.test.mazetv.main.details.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.mazetv.core.UiState
import com.test.mazetv.data.models.Episode
import com.test.mazetv.data.models.ShowsDetails
import com.test.mazetv.repository.ShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailsViewModel @Inject constructor(
    private val showRepository: ShowRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
  private val _showState = MutableStateFlow<UiState<ShowsDetails>>(UiState.Loading)

  val showState: StateFlow<UiState<ShowsDetails>> = _showState.asStateFlow()

  private val _seasonEpisodesState = MutableStateFlow<UiState<List<Episode>>>(UiState.Loading)

  val seasonEpisodesState: StateFlow<UiState<List<Episode>>> = _seasonEpisodesState.asStateFlow()

  private val _selectedSeasonId = MutableStateFlow<Int?>(null)

  val selectedSeasonId: StateFlow<Int?> = _selectedSeasonId.asStateFlow()

  private val showsId: Int = checkNotNull(savedStateHandle["showsId"])

  init {
    fetchShowDetails()
  }

  private fun fetchShowDetails() {
    viewModelScope.launch {
      _showState.value = UiState.Loading
      try {
        val showsDeferred = async { showRepository.getShowsById(showsId) }
        val seasonsDeferred = async { showRepository.getSeasons(showsId) }
        val castDeferred = async { showRepository.getCast(showsId) }

        val show = showsDeferred.await()
        val seasons = seasonsDeferred.await()
        val cast = castDeferred.await()

        val showDetails =
            ShowsDetails(
                shows = show,
                seasons = seasons,
                episodes = emptyList(),
                cast = cast,
            )
        _showState.value = UiState.Success(showDetails)
      } catch (e: Exception) {
        _showState.value = UiState.Error(e.message ?: "Unknown Error")
      }
    }
  }

  fun selectSeason(seasonId: Int) {
    _selectedSeasonId.value = seasonId
    viewModelScope.launch {
      _seasonEpisodesState.value = UiState.Loading
      try {
        val episodes = showRepository.getEpisodesBySeason(seasonId)
        _seasonEpisodesState.value = UiState.Success(episodes)
      } catch (e: Exception) {
        _seasonEpisodesState.value = UiState.Error(e.message ?: "Unknown Error")
      }
    }
  }
}
