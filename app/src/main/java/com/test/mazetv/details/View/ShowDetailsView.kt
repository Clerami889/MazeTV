package com.test.mazetv.details.View

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.test.mazetv.R
import com.test.mazetv.core.UiState
import com.test.mazetv.details.ViewModel.ShowDetailsViewModel
import com.test.mazetv.helper.shareShow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDetailsView(
    onBackClick: () -> Unit,
    viewModel: ShowDetailsViewModel = hiltViewModel(),
) {
  val showState by viewModel.showState.collectAsStateWithLifecycle()
  val show = showState
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

              if (show is UiState.Success) {
                IconButton(onClick = { shareShow(context, show.data) }) {
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
          val show = state.data

          Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            AsyncImage(
                model = show.image.medium,
                contentDescription = show.name,
                modifier = Modifier.fillMaxWidth().height(300.dp),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(16.dp))
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
            Text(text = show.summary)
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
