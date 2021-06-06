package com.omersakalli.marvelcomics.ui.comicslist

import com.omersakalli.marvelcomics.ui.model.Comic

interface OnComicClickListener {
    fun onComicClick(comic: Comic)
}