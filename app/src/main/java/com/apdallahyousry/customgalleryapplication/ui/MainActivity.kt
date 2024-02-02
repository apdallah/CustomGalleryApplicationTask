package com.apdallahyousry.customgalleryapplication.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.apdallahyousry.customgalleryapplication.R
import com.apdallahyousry.customgalleryapplication.databinding.ActivityMainBinding
import com.apdallahyousry.customgalleryapplication.helpers.PermissionHelper
import com.apdallahyousry.customgalleryapplication.helpers.PermissionHelper.PERMISSION_READ_STORAGE
import com.apdallahyousry.customgalleryapplication.ui.viewmodels.AlbumsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: AlbumsViewModel by viewModels()

    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.permssionButton.setOnClickListener {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri: Uri = Uri.fromParts("package", packageName, null)
            intent.data = uri
            startActivity(intent)
        }
        if (!PermissionHelper.checkStoragePermissions(this) ) {
            PermissionHelper.requestForStoragePermissions(this)
        }
    }
    private fun onStoragePermissionGranted(){
        viewModel.loadAlbums()
    }

    override fun onResume() {
        super.onResume()
        if (PermissionHelper.checkStoragePermissions(this) ) {
            onStoragePermissionGranted()
            binding.permssionButton.isVisible=false
        }
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

                if(permissionGranted){
                    binding.permssionButton.isVisible=false
                    onStoragePermissionGranted()
                }
                else{
                    binding.permssionButton.isVisible=true
                }
            }
        }
    }
}