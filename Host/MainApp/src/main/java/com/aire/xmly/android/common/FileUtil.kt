package com.aire.xmly.android.common

import android.content.Context
import androidx.annotation.RawRes
import okhttp3.internal.closeQuietly
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder

/**
 * Created by ZhuPeipei on 2021/3/24 21:20.
 */
class FileUtil {
    companion object {
        fun getRawFile(context: Context?, @RawRes rawId: Int): StringBuilder? =
            with(StringBuilder()) {
                var inputStream: InputStream? = null
                var br: BufferedReader? = null
                try {
                    inputStream = context?.resources?.openRawResource(rawId)
                    br = BufferedReader(InputStreamReader(inputStream))
                    var line: String? = null
                    do {
                        line = br.readLine()
                        if (line != null) {
                            append(line)
                        }
                    } while (line != null)
                    return this
                } catch (e: Exception) {
                    e.printStackTrace()
                    return null
                } finally {
                    try {
                        inputStream?.close()
                        br?.close()
                    } catch (e: Exception) {
                    }
                }
            }
    }
}