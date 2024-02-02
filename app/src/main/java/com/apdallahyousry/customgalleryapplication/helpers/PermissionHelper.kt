package com.apdallahyousry.customgalleryapplication.helpers

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


object PermissionHelper {

    const val PERMISSION_READ_STORAGE = 100


    fun checkStoragePermissions(context: Context): Boolean {
       return  ContextCompat.checkSelfPermission(context, getReadPermission()) == PackageManager.PERMISSION_GRANTED
    }
    fun requestForStoragePermissions(context: Activity) {
        ActivityCompat.requestPermissions(
            context, arrayOf(
               getReadPermission()
            ),
            PERMISSION_READ_STORAGE
        )
    }
     private fun getReadPermission():String{
         return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
             //Android is 11 (R) or above
             Manifest.permission.READ_MEDIA_IMAGES
         } else {
             //Below android 11
             Manifest.permission.READ_EXTERNAL_STORAGE
         }

     }
}