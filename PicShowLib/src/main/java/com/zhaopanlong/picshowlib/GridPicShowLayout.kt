package com.zhaopanlong.picshowlib

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fondesa.recyclerviewdivider.dividerBuilder

class GridPicShowLayout : RecyclerView {
    var maxLength = 6
    val spanCount = 3
    private var spaceSize = 20
    val imagePaths = arrayListOf<String>()
    val mAdapter = GridPicAdapter()

    private var mPlusOnClickListener: OnClickListener? = null

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        layoutManager = GridLayoutManager(context, spanCount)
        adapter = mAdapter
       context.dividerBuilder()
            .color(Color.TRANSPARENT)
            .size(spaceSize, TypedValue.COMPLEX_UNIT_DIP)
            .build()
            .addTo(this)
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
        imagePaths.add(path)
        mAdapter.notifyDataSetChanged()
    }

    fun addImagePaths(path: List<String>) {
        imagePaths.addAll(path)
        mAdapter.notifyDataSetChanged()
    }

    fun setImagePaths(path: List<String>) {
        imagePaths.clear()
        imagePaths.addAll(path)
        mAdapter.notifyDataSetChanged()
    }

    fun setOnPlusClickListen(listener: OnClickListener) {
        mPlusOnClickListener = listener
    }

    fun setSpaceSize(size: Int) {
        spaceSize = size
        mAdapter.notifyDataSetChanged()
    }


    inner class IteamViewVh(view: View) : ViewHolder(view) {

        val ivPic = view.findViewById<ImageView>(R.id.ivPic)
        val ivDelete = view.findViewById<ImageView>(R.id.ivDelete)
    }

    inner class PlusIteamVh(view: View) : ViewHolder(view) {

    }



}