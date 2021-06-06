package com.omersakalli.marvelcomics.utils

import com.omersakalli.marvelcomics.data.model.Thumbnail
import com.omersakalli.marvelcomics.utils.ImageUtils.extractUrl
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class ImageUtilsTest{

    private lateinit var thumbnail: Thumbnail

    @Before
    fun setup(){
        thumbnail = Thumbnail("jpg","http://i.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73")
    }

    @Test
    fun extractSmallPortraitImageUrl_isCorrect(){
        val finalUrl = thumbnail.extractUrl(ImageUtils.AspectRatio.PORTRAIT, 100)
        assertEquals("http://i.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73/portrait_small.jpg",finalUrl)
    }

    @Test
    fun extractMediumLandscapeImageUrl_isCorrect(){
        val finalUrl = thumbnail.extractUrl(ImageUtils.AspectRatio.LANDSCAPE,140)
        assertEquals("http://i.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73/landscape_medium.jpg",finalUrl)
    }

    @Test
    fun extractLargeStandardImageUrl_isCorrect(){
        val finalUrl = thumbnail.extractUrl(ImageUtils.AspectRatio.STANDARD,200)
        assertEquals("http://i.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73/standard_xlarge.jpg",finalUrl)
    }
}