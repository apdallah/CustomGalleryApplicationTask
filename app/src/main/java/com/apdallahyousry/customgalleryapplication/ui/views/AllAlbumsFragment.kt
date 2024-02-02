package com.apdallahyousry.customgalleryapplication.ui.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.GridLayout
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.apdallahyousry.customgalleryapplication.R
import com.apdallahyousry.customgalleryapplication.data.models.AlbumModel
import com.apdallahyousry.customgalleryapplication.databinding.LayoutFragmentAllAlbumsBinding
import com.apdallahyousry.customgalleryapplication.ui.adapters.AlbumsAdapter
import com.apdallahyousry.customgalleryapplication.ui.viewmodels.AlbumsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllAlbumsFragment : Fragment() {
    private var _binding: LayoutFragmentAllAlbumsBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: AlbumsViewModel by activityViewModels()
    private var adapter :AlbumsAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutFragmentAllAlbumsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.albumsLiveData.observe(viewLifecycleOwner) {
            binding.emptyLy.isVisible=it.isEmpty()
            bindRecyclerData(it)
        }
        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
        }
        viewModel.isLoading.observe(viewLifecycleOwner){
           binding.progressView.isVisible=it
        }
        binding.topToolBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.actionToggleGridLinear->{
                    val isGrid=(binding.albumsRv.layoutManager as GridLayoutManager).spanCount>1

                    if (isGrid){
                        (binding.albumsRv.layoutManager as GridLayoutManager).spanCount=1
                        it.setIcon(R.drawable.ic_linear)
                    }else{
                        (binding.albumsRv.layoutManager as GridLayoutManager).spanCount=3
                        it.setIcon(R.drawable.ic_grid)

                    }
                    adapter?.switchLayoutManager(isGrid)

                }
            }
            return@setOnMenuItemClickListener false
        }

    }

    private fun bindRecyclerData(data: List<AlbumModel>) {
       adapter = AlbumsAdapter(data) {
            viewModel.onAlbumSelected(it)
           findNavController().navigate(R.id.action_allAlbumsFragment_to_albumDetailsFragment)
        }
        binding.albumsRv.adapter = adapter
        binding.albumsRv.layoutManager = GridLayoutManager(requireContext(), 3)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}