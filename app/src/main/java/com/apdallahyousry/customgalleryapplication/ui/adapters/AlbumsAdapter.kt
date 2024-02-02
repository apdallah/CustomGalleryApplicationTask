package com.apdallahyousry.customgalleryapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.apdallahyousry.customgalleryapplication.data.models.AlbumModel
import com.apdallahyousry.customgalleryapplication.databinding.LayoutItemAlbumBinding
import com.apdallahyousry.customgalleryapplication.ui.viewmodels.AlbumsViewModel

class AlbumsAdapter(private val albumsList: List<AlbumModel>,private val onItemClicked:(item:AlbumModel)->Unit):RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        return AlbumsViewHolder(LayoutItemAlbumBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return albumsList.size
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.bindData(albumsList[position], onItemClicked = onItemClicked)
    }
    class AlbumsViewHolder(private val binding: LayoutItemAlbumBinding) : ViewHolder(binding.root) {
        fun bindData(item: AlbumModel,onItemClicked: (item: AlbumModel) -> Unit) {
            binding.albumCountTv.text=item.mediaItems.size.toString()
            binding.albumTitleTv.text=item.title
            binding.root.setOnClickListener {
                onItemClicked.invoke(item)
            }
        }
    }
}