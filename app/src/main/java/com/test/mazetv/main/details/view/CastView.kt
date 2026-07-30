package com.test.mazetv.main.details.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.test.mazetv.data.models.Cast

@Composable
fun CastView(cast: Cast) {
  Column(
      modifier = Modifier.width(100.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    AsyncImage(
        model = cast.person.image?.medium,
        contentDescription = cast.person.name,
        modifier = Modifier.size(80.dp).clip(CircleShape),
        contentScale = ContentScale.Crop,
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = cast.person.name,
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
    Text(
        text = cast.character.name,
        style = MaterialTheme.typography.labelSmall,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
  }
}
