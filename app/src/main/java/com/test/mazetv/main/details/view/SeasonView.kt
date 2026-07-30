package com.test.mazetv.main.details.view

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.test.mazetv.data.models.Season

@Composable
fun SeasonView(
    season: Season,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
  Card(
      modifier =
          Modifier.width(140.dp)
              .clickable { onClick() }
              .then(
                  if (isSelected)
                      Modifier.border(
                          2.dp,
                          MaterialTheme.colorScheme.primary,
                          RoundedCornerShape(8.dp),
                      )
                  else Modifier
              ),
      shape = RoundedCornerShape(8.dp),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
      colors =
          CardDefaults.cardColors(
              containerColor =
                  if (isSelected) MaterialTheme.colorScheme.primaryContainer
                  else MaterialTheme.colorScheme.surface
          ),
  ) {
    Column {
      AsyncImage(
          model = season.image?.medium,
          contentDescription = season.name,
          modifier =
              Modifier.fillMaxWidth()
                  .height(180.dp)
                  .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
          contentScale = ContentScale.Crop,
      )
      Text(
          text = "Season ${season.number}",
          modifier = Modifier.padding(8.dp),
          style = MaterialTheme.typography.titleSmall,
          color =
              if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer
              else MaterialTheme.colorScheme.onSurface,
      )
    }
  }
}
