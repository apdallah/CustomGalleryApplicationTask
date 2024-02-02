package com.apdallahyousry.customgalleryapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.apdallahyousry.customgalleryapplication.R
import com.apdallahyousry.customgalleryapplication.data.models.AlbumModel
import com.apdallahyousry.customgalleryapplication.data.models.MediaItemModel
import com.apdallahyousry.customgalleryapplication.databinding.LayoutItemAlbumBinding
import com.apdallahyousry.customgalleryapplication.databinding.LayoutItemAlbumLinearBinding
import com.apdallahyousry.customgalleryapplication.ui.viewmodels.AlbumsViewModel
import com.bumptech.glide.Glide
 import java.io.File

class AlbumsAdapter(
    private val albumsList: List<AlbumModel>,
    private val onItemClicked: (item: AlbumModel) -> Unit
) : ListAdapter<AlbumModel,ViewHolder>(AlbumDiffUtil) {
    object AlbumDiffUtil : DiffUtil.ItemCallback<AlbumModel>() {
        override fun areItemsTheSame(oldItem: AlbumModel, newItem: AlbumModel): Boolean {
            //This is to check if the item its self is the same this will use the equality function of the object.
            //Alternatively if you are using a database and have a id you can you that.
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: AlbumModel, newItem: AlbumModel): Boolean {
            //This Goes a bit deeper and checks to make sure the data of the object is the same.
            return oldItem.title == newItem.title
        }

    }
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
                Glide.with(binding.root.context).load(it).
                error(R.drawable.ic_launcher_background)
                    .centerCrop().placeholder(R.drawable.ic_launcher_background)
                    .into(binding.thumbinalImageView)

            }
            val drawable=if (item.title.equals("All Images",true))R.drawable.ic_all_images
            else if(item.title.equals("camera",true))R.drawable.ic_camera
            else R.drawable.ic_folder
                Glide.with(binding.root.context).load(drawable).into(binding.albumIcon)

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
                Glide.with(binding.root.context).load(it).
                    error(R.drawable.ic_launcher_background)
                    .centerCrop().placeholder(R.drawable.ic_launcher_background)
                    .into(binding.thumbinalImageView)


            }
            val drawable=if (item.title.equals("All Images",true))R.drawable.ic_all_images
            else if(item.title.equals("camera",true))R.drawable.ic_camera
            else R.drawable.ic_folder
            Glide.with(binding.root.context).load(drawable).into(binding.albumIcon)
        }
    }
}