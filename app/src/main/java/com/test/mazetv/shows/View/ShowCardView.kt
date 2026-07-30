package com.test.mazetv.shows.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.test.mazetv.R
import com.test.mazetv.data.models.Shows

@Composable
fun ShowCardView(shows: Shows) {
  Card(
      modifier = Modifier.fillMaxSize().padding(vertical = 8.dp),
      elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
  ) {
    Row(modifier = Modifier.padding(16.dp)) {
      Column() {
        Text(text = shows.name)
        Row() {
          Spacer(Modifier.width(4.dp))
          Text(text = shows.rating.average?.toString() ?: stringResource(R.string.RatingFallback))
        }
      }
    }
  }
}
