package com.apdallahyousry.customgalleryapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.apdallahyousry.customgalleryapplication.R
import com.apdallahyousry.customgalleryapplication.data.models.AlbumModel
import com.apdallahyousry.customgalleryapplication.databinding.LayoutItemAlbumBinding
import com.apdallahyousry.customgalleryapplication.databinding.LayoutItemAlbumLinearBinding
import com.apdallahyousry.customgalleryapplication.ui.viewmodels.AlbumsViewModel
import com.squareup.picasso.Picasso
import java.io.File

class AlbumsAdapter(
    private val albumsList: List<AlbumModel>,
    private val onItemClicked: (item: AlbumModel) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    val GRID_VIEW_TYPE = 0
    val LINEAR_VIEW_TYPE = 1
    var displayViewType = GRID_VIEW_TYPE
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return when (displayViewType) {
            LINEAR_VIEW_TYPE -> AlbumsLinearViewHolder(
                LayoutItemAlbumLinearBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> AlbumsViewHolder(
                LayoutItemAlbumBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return displayViewType
    }

    override fun getItemCount(): Int {
        return albumsList.size
    }

    fun switchLayoutManager(isGrid: Boolean) {
        displayViewType =
            if (isGrid) LINEAR_VIEW_TYPE else GRID_VIEW_TYPE
        notifyItemRangeChanged(0,albumsList.size)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is AlbumsViewHolder) holder.bindData(
            albumsList[position],
            onItemClicked = onItemClicked
        )
        else if (holder is AlbumsLinearViewHolder) holder.bindData(
            albumsList[position],
            onItemClicked = onItemClicked
        )
    }

    class AlbumsViewHolder(private val binding: LayoutItemAlbumBinding) : ViewHolder(binding.root) {
        fun bindData(item: AlbumModel, onItemClicked: (item: AlbumModel) -> Unit) {
            binding.albumCountTv.text = item.mediaItems.size.toString()
            binding.albumTitleTv.text = item.title
            binding.root.setOnClickListener {
                onItemClicked.invoke(item)
            }
            item.thumbnail?.let {
                Picasso.get().load(File(it)).error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background).fit()
                    .into(binding.thumbinalImageView)

            }
        }
    }

    class AlbumsLinearViewHolder(private val binding: LayoutItemAlbumLinearBinding) :
        ViewHolder(binding.root) {
        fun bindData(item: AlbumModel, onItemClicked: (item: AlbumModel) -> Unit) {
            binding.albumCountTv.text = item.mediaItems.size.toString()
            binding.albumTitleTv.text = item.title
            binding.root.setOnClickListener {
                onItemClicked.invoke(item)
            }
            item.thumbnail?.let {
                Picasso.get().load(File(it)).error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background).fit()
                    .into(binding.thumbinalImageView)

            }
        }
    }
}