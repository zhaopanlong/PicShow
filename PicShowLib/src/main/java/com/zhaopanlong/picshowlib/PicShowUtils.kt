package com.zhaopanlong.picshowlib

import android.content.Context
import android.widget.ImageView

object PicShowUtils {
    fun showUrlImage(context: Context, imageUrls: ArrayList<String>, currentPostion: Int) {
        PicShowActivity.showUrlImage(context, imageUrls, currentPostion)
    }

    fun showUrlBase64(context: Context, base64Images: ArrayList<String>, currentPostion: Int) {
        PicShowActivity.showUrlBase64(context, base64Images, currentPostion)
    }

    fun showImageViews(context: Context, imageViews: ArrayList<ImageView>, currentPostion: Int) {
        PicShowActivity.showImageViews(context, imageViews, currentPostion)
    }
}