package com.test.mazetv.main.shows.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.test.mazetv.core.UiState
import com.test.mazetv.main.shows.viewModel.ShowViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowView(
    onShowClick: (Int) -> Unit,
    viewModel: ShowViewModel = hiltViewModel(),
) {
  val showState by viewModel.showState.collectAsStateWithLifecycle()

  Scaffold(
      topBar = {
        TopAppBar(
            title = { Text(text = stringResource(com.test.mazetv.R.string.ShowViewTitle)) },
            colors =
                TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
        )
      }
  ) { Padding ->
    when (val state = showState) {
      is UiState.Loading -> {
        Box(
            modifier = Modifier.fillMaxSize().padding(Padding),
            contentAlignment = Alignment.Center,
        ) {
          CircularProgressIndicator()
        }
      }

      is UiState.Success -> {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(Padding).padding(horizontal = 16.dp),
        ) {
          items(state.data) { shows ->
            ShowCardView(shows = shows, onClick = { onShowClick(shows.id) })
          }
        }
      }

      is UiState.Error -> {
        Box(
            modifier = Modifier.fillMaxSize().padding(Padding),
            contentAlignment = Alignment.Center,
        ) {
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = state.message,
                color = MaterialTheme.colorScheme.error,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { viewModel.retry() }) {
              Text(stringResource(com.test.mazetv.R.string.ShowViewRetry))
            }
          }
        }
      }
    }
  }
}
