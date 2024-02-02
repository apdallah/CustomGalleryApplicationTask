package com.apdallahyousry.customgalleryapplication.ui.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.apdallahyousry.customgalleryapplication.databinding.LayoutFragmentAllAlbumsBinding
import com.apdallahyousry.customgalleryapplication.ui.viewmodels.AlbumsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllAlbumsFragment:Fragment() {
    private var _binding: LayoutFragmentAllAlbumsBinding?=null
    private val binding
        get() = _binding!!

    private val viewModel: AlbumsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= LayoutFragmentAllAlbumsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.albumsLiveData.observe(viewLifecycleOwner){
            Log.i(this::class.simpleName, "onViewCreated: ${it.size}")
        }
        binding.topToolBar

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}