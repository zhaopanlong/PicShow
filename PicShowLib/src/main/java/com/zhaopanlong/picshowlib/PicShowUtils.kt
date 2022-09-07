package com.zhaopanlong.picshowlib

import android.content.Context
import android.widget.ImageView

object PicShowUtils {
    fun showUrlImage(context: Context, imageUrls: ArrayList<String>, position: Int) {
        PicShowActivity.showUrlImage(context, imageUrls, position)
    }

    fun showBase64Image(context: Context, base64Images: ArrayList<String>, position: Int) {
        PicShowActivity.showUrlBase64(context, base64Images, position)
    }

    fun showImageViews(context: Context, imageViews: ArrayList<ImageView>, position: Int) {
        PicShowActivity.showImageViews(context, imageViews, position)
    }
}