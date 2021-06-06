package com.omersakalli.marvelcomics.utils

import java.nio.charset.StandardCharsets.UTF_8
import java.security.MessageDigest

object EncryptionUtils {

    // Black magic stolen from stackoverflow
    fun md5Hash(str: String): String =
        MessageDigest.getInstance("MD5").digest(str.toByteArray(UTF_8))
            .joinToString(separator = "") { byte -> "%02x".format(byte) }

}