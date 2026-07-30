package com.test.mazetv.main.details.view

import android.text.Html
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.test.mazetv.R
import com.test.mazetv.core.UiState
import com.test.mazetv.helper.shareShow
import com.test.mazetv.main.details.viewModel.ShowDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDetailsView(
    onBackClick: () -> Unit,
    viewModel: ShowDetailsViewModel = hiltViewModel(),
) {
  val showState by viewModel.showState.collectAsStateWithLifecycle()
  val seasonEpisodesState by viewModel.seasonEpisodesState.collectAsStateWithLifecycle()
  val selectedSeasonId by viewModel.selectedSeasonId.collectAsStateWithLifecycle()
  val show = showState

  var showFullImage by remember { mutableStateOf(false) }

  if (showFullImage && showState is UiState.Success) {
    val show = (showState as UiState.Success).data.shows
    Dialog(
        onDismissRequest = { showFullImage = false },
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
      Box(
          modifier = Modifier.fillMaxSize().clickable { showFullImage = false },
          contentAlignment = Alignment.Center,
      ) {
        AsyncImage(
            model = show.image.medium,
            contentDescription = show.name,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
        )
      }
    }
  }

  Scaffold(
      topBar = {
        TopAppBar(
            title = { Text(stringResource(R.string.ShowDetailsTitle)) },
            navigationIcon = {
              IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                )
              }
            },
            actions = {
              val context = LocalContext.current
              val state = showState
              if (state is UiState.Success) {
                IconButton(onClick = { shareShow(context, shows = state.data.shows) }) {
                  Icon(
                      imageVector = Icons.Default.Share,
                      contentDescription = "Share",
                  )
                }
              }
            },
        )
      }
  ) { padding ->
    Box(modifier = Modifier.fillMaxSize().padding(padding)) {
      when (val state = showState) {
        is UiState.Loading -> {
          CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        is UiState.Success -> {
          val showsDetails = state.data
          val show = showsDetails.shows
          val summary = Html.fromHtml(show.summary, Html.FROM_HTML_MODE_LEGACY)
          LazyColumn(
              modifier = Modifier.fillMaxSize(),
              contentPadding = PaddingValues(bottom = 16.dp),
          ) {
            item {
              AsyncImage(
                  model = show.image.medium,
                  contentDescription = show.name,
                  modifier =
                      Modifier.fillMaxWidth().height(400.dp).clickable { showFullImage = true },
                  contentScale = ContentScale.Crop,
              )
            }
            // ? Movie
            item {
              Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = show.name,
                    style = MaterialTheme.typography.headlineMedium,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Icon(
                      imageVector = Icons.Default.Star,
                      contentDescription = null,
                      tint = MaterialTheme.colorScheme.primary,
                      modifier = Modifier.size(24.dp),
                  )
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                      text = show.rating.average?.toString() ?: "N/A",
                      style = MaterialTheme.typography.bodyLarge,
                  )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Premiere Date : ${show.premiered}",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Summary",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = summary.toString())
              }
            }

            // ? Cast
            if (showsDetails.cast.isNotEmpty()) {
              item {
                Text(
                    text = "Cast",
                    style = MaterialTheme.typography.titleLarge,
                    modifier =
                        Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 8.dp,
                        ),
                    fontWeight = FontWeight.Bold,
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                  items(showsDetails.cast) { cast ->
                    CastView(cast)
                  }
                }
              }
            }

            // ? Seasons
            if (showsDetails.seasons.isNotEmpty()) {
              item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Seasons",
                    style = MaterialTheme.typography.titleLarge,
                    modifier =
                        Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 8.dp,
                        ),
                    fontWeight = FontWeight.Bold,
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                  items(showsDetails.seasons) { season ->
                    SeasonView(
                        season = season,
                        isSelected = season.id == selectedSeasonId,
                        onClick = { viewModel.selectSeason(season.id) },
                    )
                  }
                }
              }
            }

            // ? Episodes
            when (val epsState = seasonEpisodesState) {
              is UiState.Loading -> {
                item {
                  Box(
                      modifier = Modifier.padding(padding).fillMaxWidth().padding(32.dp),
                      contentAlignment = Alignment.Center,
                  ) {
                    CircularProgressIndicator()
                  }
                }
              }

              is UiState.Success -> {
                items(epsState.data) { episode ->
                  EpisodeView(episode)
                }
              }

              is UiState.Error -> {
                item {
                  Text(
                      text = epsState.message,
                      color = MaterialTheme.colorScheme.error,
                      modifier = Modifier.padding(16.dp),
                  )
                }
              }
            }
          }
        }

        is UiState.Error -> {
          Text(
              text = state.message,
              color = MaterialTheme.colorScheme.error,
              modifier = Modifier.align(Alignment.Center),
          )
        }
      }
    }
  }
}
