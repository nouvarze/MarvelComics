package com.omersakalli.marvelcomics.utils

import android.util.DisplayMetrics
import com.omersakalli.marvelcomics.data.model.Thumbnail

object ImageUtils {

    /**
     * @returns custom URL that fits to your needs
     * @param aspectRatio Determines the aspect ratio of the picture
     * @param densityDpi  Used for determining resolution of the picture. You can get it from context.resources.displayMetrics.densityDpi
     */
    fun Thumbnail.extractUrl(aspectRatio: AspectRatio, densityDpi: Int = 240): String {
        var url = "$path/${aspectRatio.string}_"
        if (aspectRatio == AspectRatio.PORTRAIT)
            url += when (densityDpi) {
                in ScreenDensities.SMALL.range -> ScreenDensities.SMALL.string
                in ScreenDensities.MEDIUM.range -> ScreenDensities.MEDIUM.string
                in ScreenDensities.XLARGE.range -> ScreenDensities.XLARGE.string
                in ScreenDensities.FANTASTIC.range -> ScreenDensities.FANTASTIC.string
                in ScreenDensities.UNCANNY.range -> ScreenDensities.UNCANNY.string
                in ScreenDensities.INCREDIBLE.range -> ScreenDensities.INCREDIBLE.string
                else -> ScreenDensities.INCREDIBLE.string
            }
        else //Landscape
            url += when (densityDpi) {
                in ScreenDensities.SMALL.range -> ScreenDensities.SMALL.string
                in ScreenDensities.MEDIUM.range -> ScreenDensities.MEDIUM.string
                in ScreenDensities.XLARGE.range -> "large"
                in ScreenDensities.FANTASTIC.range -> ScreenDensities.XLARGE.string
                in ScreenDensities.UNCANNY.range -> "amazing"
                in ScreenDensities.INCREDIBLE.range -> ScreenDensities.INCREDIBLE.string
                else -> ScreenDensities.INCREDIBLE.string
            }
        url += ".$extension"
        return url
    }

    /**
     * @returns URL of full size image
     */
    fun Thumbnail.extractUrl(aspectRatio: AspectRatio, isIncredibleResolution: Boolean): String =
        extractUrl(aspectRatio, ScreenDensities.INCREDIBLE.range.first)

    /**
     * @returns URL of full size image
     */
    fun Thumbnail.extractUrl(): String = "$path.$extension"

    enum class AspectRatio(val string: String) {
        PORTRAIT("portrait"),

        //STANDARD("standard"), //Not supported in our app
        LANDSCAPE("landscape")
    }

    //This part was written according to the portrait modes. Landscape & standard modes have different namings to them
    private enum class ScreenDensities(val string: String, val range: IntRange) {
        SMALL("small", 0..DisplayMetrics.DENSITY_LOW),
        MEDIUM("medium", DisplayMetrics.DENSITY_LOW + 1..DisplayMetrics.DENSITY_MEDIUM),
        XLARGE("xlarge", DisplayMetrics.DENSITY_MEDIUM + 1..DisplayMetrics.DENSITY_HIGH),
        FANTASTIC("fantastic", DisplayMetrics.DENSITY_HIGH + 1..DisplayMetrics.DENSITY_XHIGH),
        UNCANNY("uncanny", DisplayMetrics.DENSITY_XHIGH + 1..DisplayMetrics.DENSITY_XXHIGH),
        INCREDIBLE("incredible", DisplayMetrics.DENSITY_XXHIGH + 1..DisplayMetrics.DENSITY_XXXHIGH)
    }

}