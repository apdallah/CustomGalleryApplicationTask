package com.apdallahyousry.customgalleryapplication.data.models

data class MediaItemModel(val id:Int,
                          val title:String,
                          val albumName:String?=null,
                          val mediaPath:String?=null,
                          val mediaType:MediaType,
    )
enum class MediaType{
    MEDIA_TYPE_IMAGE,
    MEDIA_TYPE_VIDEO
}