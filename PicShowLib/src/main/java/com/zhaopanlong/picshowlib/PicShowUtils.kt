package com.zhaopanlong.picshowlib

import android.content.Context
import android.widget.ImageView

object PicShowUtils {
    fun showUrlImage(context: Context, imageUrls: List<String>, position: Int) {
        PicShowActivity.showUrlImage(context, imageUrls, position)
    }

    fun showBase64Image(context: Context, base64Images: List<String>, position: Int) {
        PicShowActivity.showUrlBase64(context, base64Images, position)
    }

    fun showImageViews(context: Context, imageViews: List<ImageView>, position: Int) {
        PicShowActivity.showImageViews(context, imageViews, position)
    }
}