package com.test.mazetv.shows.View

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.test.mazetv.R
import com.test.mazetv.shows.ViewModel.ShowViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowView(viewModel: ShowViewModel = hiltViewModel()) {
  val show = viewModel.show.collectAsState().value

  Scaffold(
      topBar = {
        TopAppBar(
            title = { Text(text = stringResource(R.string.ShowViewTitle)) },
            colors =
                TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                ),
        )
      }
  ) { Padding ->
    LazyColumn(modifier = Modifier.fillMaxSize().padding(Padding).padding(16.dp)) {
      items(show) { shows -> ShowCardView(shows) }
    }
  }
}
