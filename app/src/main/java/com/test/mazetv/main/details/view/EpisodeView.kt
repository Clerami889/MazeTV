package com.test.mazetv.main.details.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.test.mazetv.data.models.Episode

@Composable
fun EpisodeView(episode: Episode) {
  Card(
      modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 4.dp),
      shape = RoundedCornerShape(8.dp),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
  ) {
    Row(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
      AsyncImage(
          model = episode.image?.medium,
          contentDescription = episode.name,
          modifier = Modifier.size(80.dp).clip(RoundedCornerShape(4.dp)),
          contentScale = ContentScale.Crop,
      )
      Spacer(modifier = Modifier.width(12.dp))
      Column {
        Text(
            text = "E${episode.number}: ${episode.name}",
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = "Season ${episode.season}",
            style = MaterialTheme.typography.bodySmall,
        )
      }
    }
  }
}
