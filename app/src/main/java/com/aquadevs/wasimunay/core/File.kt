package com.aquadevs.wasimunay.core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.Locale

object File {
    fun saveBitmapToFile(context: Context, bitmap: Bitmap, fileName: String, nameDirectory:String) {
        context.apply {
            val directory = File(context.cacheDir, nameDirectory)

            if (!directory.exists()) {
                directory.mkdirs()
            }

            val file = File(directory, "$fileName.png")
            var fos: FileOutputStream? = null

            try {
                fos = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
                fos.flush()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                fos?.close()
            }
        }
    }

    fun getCacheNameDirectories(context: Context): List<String> {
        val cacheDirectory = context.cacheDir
        val directories = mutableListOf<String>()

        if (cacheDirectory.exists() && cacheDirectory.isDirectory) {
            val files = cacheDirectory.listFiles()
            files?.forEach { file ->
                if (file.isDirectory) {
                    directories.add(file.name)
                }
            }
        }

        return directories
    }

    fun getImagesFromCacheDirectory(context: Context, directoryName: String): List<Bitmap> {
        val cacheDirectory = File(context.cacheDir, directoryName)
        val bitmaps = mutableListOf<Bitmap>()

        if (cacheDirectory.exists() && cacheDirectory.isDirectory) {
            val files = cacheDirectory.listFiles()
            files?.forEach { file ->
                if (file.isFile && isImageFile(file)) {
                    val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                    if (bitmap != null) {
                        bitmaps.add(bitmap)
                    }
                }
            }
        }

        return bitmaps
    }

    fun deleteCacheDirectory(context: Context, directoryName: String): Boolean {
        val cacheDir = File(context.cacheDir, directoryName)
        return deleteRecursively(cacheDir)
    }

    private fun deleteRecursively(fileOrDirectory: File): Boolean {
        if (fileOrDirectory.isDirectory) {
            fileOrDirectory.listFiles()?.forEach { child ->
                deleteRecursively(child)
            }
        }
        return fileOrDirectory.delete()
    }

    private fun isImageFile(file: File): Boolean {
        val imageExtensions = listOf("jpg", "jpeg", "png", "bmp", "gif")
        val extension = file.extension.toLowerCase(Locale.ROOT)
        return imageExtensions.contains(extension)
    }
}