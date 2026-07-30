package com.test.mazetv.helper

import android.content.Context
import android.content.Intent
import android.text.Html
import com.test.mazetv.data.models.Shows

fun shareShow(context: Context, shows: Shows) {
  val summary = Html.fromHtml(shows.summary ?: "", Html.FROM_HTML_MODE_LEGACY).toString()
  val shareContent =
      """
        ${shows.name}
        $summary
        ${shows.url}
    """
          .trimIndent()

  val intent =
      Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, shareContent)
      }
  context.startActivity(Intent.createChooser(intent, "Share TV Show"))
}
