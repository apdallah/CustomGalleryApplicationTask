package com.apdallahyousry.customgalleryapplication.ui

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.apdallahyousry.customgalleryapplication.R
import com.apdallahyousry.customgalleryapplication.helpers.PermissionHelper
import com.apdallahyousry.customgalleryapplication.helpers.PermissionHelper.PERMISSION_READ_STORAGE
import com.apdallahyousry.customgalleryapplication.ui.viewmodels.AlbumsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val viewModel: AlbumsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!PermissionHelper.checkStoragePermissions(this) ) {
            PermissionHelper.requestForStoragePermissions(this)
        }else{
            onStoragePermissionGranted()
        }
    }
    private fun onStoragePermissionGranted(){
        viewModel.loadAlbums()
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_READ_STORAGE -> {
                val permissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED

                if(permissionGranted)onStoragePermissionGranted()
            }
        }
    }
}