package com.test.mazetv.shows.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.test.mazetv.R
import com.test.mazetv.data.models.Shows

@Composable
fun ShowCardView(shows: Shows, onClick: () -> Unit) {
  Card(
      colors =
          CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
      modifier = Modifier.fillMaxSize().padding(vertical = 8.dp),
      elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
      onClick = onClick,
  ) {
    Row(modifier = Modifier.padding(16.dp)) {
      AsyncImage(
          model = shows.image.medium,
          contentDescription = stringResource(R.string.CardViewImageDescription),
          modifier = Modifier,
          contentScale = ContentScale.Crop,
      )
      Column(Modifier.padding(top = 24.dp, start = 12.dp)) {
        Text(text = shows.name, color = MaterialTheme.colorScheme.onSurface)
        Row() {
          Icon(
              imageVector = Icons.Default.Star,
              contentDescription = stringResource(R.string.CardViewIconDescription),
          )
          Spacer(Modifier.width(4.dp))
          Text(text = shows.rating.average?.toString() ?: stringResource(R.string.RatingFallback))
        }
      }
    }
  }
}
