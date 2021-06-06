package com.omersakalli.marvelcomics.utils

import android.os.Build
import android.text.Html
import android.text.Spanned

object StringUtils {
    fun String.htmlToSpanned(): Spanned? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(this)
        }
    }
}