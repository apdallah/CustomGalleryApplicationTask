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
import com.apdallahyousry.customgalleryapplication.databinding.LayoutItemMediaBinding
import com.apdallahyousry.customgalleryapplication.ui.viewmodels.AlbumsViewModel
import com.bumptech.glide.Glide
 import java.io.File

class MediaItemsAdapter(
    private val data: List<MediaItemModel>
) :ListAdapter<MediaItemModel,MediaItemsAdapter.MediaItemViewHolder>(MediaDiffUtil) {
    object MediaDiffUtil : DiffUtil.ItemCallback<MediaItemModel>() {
        override fun areItemsTheSame(oldItem: MediaItemModel, newItem: MediaItemModel): Boolean {
            //This is to check if the item its self is the same this will use the equality function of the object.
            //Alternatively if you are using a database and have a id you can you that.
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MediaItemModel, newItem: MediaItemModel): Boolean {
            //This Goes a bit deeper and checks to make sure the data of the object is the same.
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaItemViewHolder {

        return  MediaItemViewHolder(
                LayoutItemMediaBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

    }



    override fun getItemCount(): Int {
        return data.size
    }




    override fun onBindViewHolder(holder: MediaItemViewHolder, position: Int) {
         holder.bindData(data[position])
    }

    class MediaItemViewHolder(private val binding: LayoutItemMediaBinding) : ViewHolder(binding.root) {
        fun bindData(item: MediaItemModel) {
             binding.albumTitleTv.text = item.title
            item.mediaPath?.let {
                Glide.with(binding.root.context).load(File(it)).
                error(R.drawable.ic_launcher_background)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binding.thumbinalImageView)

            }
        }
    }


}