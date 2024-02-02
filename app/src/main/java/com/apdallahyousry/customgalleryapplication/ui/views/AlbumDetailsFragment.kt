package com.apdallahyousry.customgalleryapplication.ui.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.GridLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.apdallahyousry.customgalleryapplication.R
import com.apdallahyousry.customgalleryapplication.data.models.AlbumModel
import com.apdallahyousry.customgalleryapplication.databinding.LayoutFragmentAlbumDetailsBinding
import com.apdallahyousry.customgalleryapplication.databinding.LayoutFragmentAllAlbumsBinding
import com.apdallahyousry.customgalleryapplication.ui.adapters.AlbumsAdapter
import com.apdallahyousry.customgalleryapplication.ui.adapters.MediaItemsAdapter
import com.apdallahyousry.customgalleryapplication.ui.viewmodels.AlbumsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailsFragment : Fragment() {
    private var _binding: LayoutFragmentAlbumDetailsBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: AlbumsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutFragmentAlbumDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedAlbum.observe(viewLifecycleOwner) {
            bindData(it)
        }
    }

    private fun bindData(data: AlbumModel) {
        binding.topToolBar.title=data.title
       val adapter = MediaItemsAdapter(data.mediaItems)
        binding.mediaRv.adapter = adapter
        binding.mediaRv.layoutManager = GridLayoutManager(requireContext(), 3)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}