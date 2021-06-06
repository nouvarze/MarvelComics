package com.omersakalli.marvelcomics.utils

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.util.DisplayMetrics
import android.util.Log
import com.omersakalli.marvelcomics.data.model.Thumbnail
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

//TODO: Can inject app context dependency here as it is used often
object ImageUtils {

    //TODO: Currently using "usesCleartextTraffic" in the manifest file. This might cause security weaknesses.
    // We can try to switch from http to https to eliminate this.
    /**
     * @returns custom URL that fits to your needs
     * @param aspectRatio Determines the aspect ratio of the picture
     * @param densityDpi  Used for determining resolution of the picture. You can get it from context.resources.displayMetrics.densityDpi
     */
    fun Thumbnail.extractUrl(aspectRatio: AspectRatio, densityDpi: Int = 240):String{
        var url = "$path/${aspectRatio.string}_"
        url += when(densityDpi){
            in ScreenDensities.SMALL.range -> ScreenDensities.SMALL.string
            in ScreenDensities.MEDIUM.range -> ScreenDensities.MEDIUM.string
            in ScreenDensities.XLARGE.range -> ScreenDensities.XLARGE.string
            in ScreenDensities.FANTASTIC.range -> ScreenDensities.FANTASTIC.string
            in ScreenDensities.UNCANNY.range -> ScreenDensities.UNCANNY.string
            in ScreenDensities.INCREDIBLE.range -> ScreenDensities.INCREDIBLE.string
            else -> ScreenDensities.INCREDIBLE.string
        }
        url += ".$extension"
        return url
    }

    /**
     * @returns URL of full size image
     */
    fun Thumbnail.extractUrl():String = "$path.$extension"

    enum class AspectRatio(val string: String){
        PORTRAIT("portrait"),
        STANDARD("standard"),
        LANDSCAPE("landscape")
    }

    //TODO: This part was written accordingly to the portrait modes. Landscape & standard modes have different namings to them
    private enum class ScreenDensities(val string: String, val range: IntRange){
        SMALL("small",0..DisplayMetrics.DENSITY_LOW),
        MEDIUM("medium",DisplayMetrics.DENSITY_LOW+1..DisplayMetrics.DENSITY_MEDIUM),
        XLARGE("xlarge",DisplayMetrics.DENSITY_MEDIUM+1..DisplayMetrics.DENSITY_HIGH),
        FANTASTIC("fantastic",DisplayMetrics.DENSITY_HIGH+1..DisplayMetrics.DENSITY_XHIGH),
        UNCANNY("uncanny",DisplayMetrics.DENSITY_XHIGH+1..DisplayMetrics.DENSITY_XXHIGH),
        INCREDIBLE("incredible",DisplayMetrics.DENSITY_XXHIGH+1..DisplayMetrics.DENSITY_XXXHIGH)
    }

//    //Took from https://stackoverflow.com/questions/44761720/save-picture-to-storage-using-glide & made changes
//    private fun saveImage(image: Bitmap, fileName: String): String? {
//        var savedImagePath: String? = null
//        val imageFileName = "$fileName.jpg"
//        val storageDir = File(
//            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
//                .toString() + "/marvelComics"
//        )
//        var success = true
//        if (!storageDir.exists()) {
//            success = storageDir.mkdirs()
//        }
//        if (success) {
//            val imageFile = File(storageDir, imageFileName)
//            savedImagePath = imageFile.absolutePath
//            try {
//                val fOut: OutputStream = FileOutputStream(imageFile)
//                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
//                fOut.close()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//
//            // Add the image to the system gallery
//            galleryAddPic(savedImagePath)
//            Log.d(LoggingTags.IMAGE_UTIL, "IMAGE SAVED: $fileName")
//
//        }
//        return savedImagePath
//    }
//
//    private fun galleryAddPic(imagePath: String?) {
//        imagePath?.let { path ->
//            val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
//            val f = File(path)
//            val contentUri: Uri = Uri.fromFile(f)
//            mediaScanIntent.data = contentUri
//            sendBroadcast(mediaScanIntent)
//        }
//    }
}