package com.zhaopanlong.picshowlib

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GridPicShowLayout : RecyclerView {
    private var maxLength = 6
    private val imagePaths = arrayListOf<String>()
    private val mAdapter = GridPicAdapter()

    private var mPlusOnClickListener: OnClickListener? = null

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        layoutManager = GridLayoutManager(context, 1)
        adapter = mAdapter
    }


    inner class GridPicAdapter : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            when (viewType) {
                1 -> {
                    val view = LayoutInflater.from(context).inflate(R.layout.gridpic_iteam_plus, parent, false)
                    return PlusIteamVh(view)
                }

                else -> {
                    val view = LayoutInflater.from(context).inflate(R.layout.gridpic_iteam, parent, false)
                    return IteamViewVh(view)
                }
            }
        }

        override fun getItemCount(): Int {
            if (imagePaths.size < maxLength) {
                return imagePaths.size + 1
            }
            return maxLength
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (holder is IteamViewVh) {
                Glide.with(context).load(imagePaths[position]).into(holder.ivPic)
                holder.ivDelete.setOnClickListener {
                    imagePaths.removeAt(position)
                    notifyDataSetChanged()
                }
            }

            if (holder is PlusIteamVh) {
                holder.itemView.setOnClickListener {
                    mPlusOnClickListener?.onClick(it)
                }
            }
        }

        override fun getItemViewType(position: Int): Int {
            if (position < imagePaths.size) {
                return 0
            }
            return 1
        }
    }

    fun addImagePath(path: String) {
        if (imagePaths.size == maxLength){
            return
        }
        imagePaths.add(path)
        mAdapter.notifyDataSetChanged()
    }

    fun addImagePaths(paths: List<String>) {
        if (imagePaths.size == maxLength){
            return
        }
        imagePaths.addAll(paths.subList(0,maxLength-imagePaths.size))
        mAdapter.notifyDataSetChanged()
    }

    fun setImagePaths(paths: List<String>) {
        imagePaths.clear()
        if (paths.size <= maxLength){
            imagePaths.addAll(paths)
        }else{
            imagePaths.addAll(paths.subList(0,maxLength))
        }
        mAdapter.notifyDataSetChanged()
    }

    fun getImagePaths(): List<String> {
        return imagePaths
    }

    fun setOnPlusClickListen(listener: OnClickListener) {
        mPlusOnClickListener = listener
    }


    inner class IteamViewVh(view: View) : ViewHolder(view) {
        val ivPic = view.findViewById<ImageView>(R.id.ivPic)
        val ivDelete = view.findViewById<ImageView>(R.id.ivDelete)
    }

    inner class PlusIteamVh(view: View) : ViewHolder(view) {

    }


}