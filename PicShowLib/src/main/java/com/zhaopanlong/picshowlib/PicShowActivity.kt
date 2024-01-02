package com.zhaopanlong.picshowlib

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.bm.library.PhotoView
import com.bumptech.glide.Glide

internal class PicShowActivity : AppCompatActivity() {

    lateinit var viewpager: ViewPager
    lateinit var tvZhishi: TextView

    private var mChoosePoint = 0

    /**
     * 0 = url类型
     * 1 = base64类型
     * 2 = imageView
     */
    var imgType = -1

    companion object {
        private var picBase64Images: List<String> = listOf()
        private var picImageViews: List<ImageView> = listOf()
        private var picImageUrls: List<String> = listOf()


        fun showUrlImage(context: Context, imageUrls: List<String>, postion: Int) {
            if (imageUrls == null || imageUrls.size == 0) {
                return
            }
            picImageUrls = imageUrls
            var mCurrentPostion = postion - 1
            if (postion <= 1) {
                mCurrentPostion = 0
            }
            if (postion > picImageUrls.size) {
                mCurrentPostion = picImageUrls.size - 1
            }
            val intent = Intent(context, PicShowActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("imgType", 0)
            bundle.putInt("currentPostion", mCurrentPostion)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }

        fun showUrlBase64(context: Context, base64Images: List<String>, postion: Int) {
            if (base64Images == null || base64Images.size == 0) {
                return
            }
            picBase64Images = base64Images
            var mCurrentPostion = postion - 1
            if (postion <= 1) {
                mCurrentPostion = 0
            }
            if (postion > picBase64Images.size) {
                mCurrentPostion = picBase64Images.size - 1
            }
            val intent = Intent(context, PicShowActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("imgType", 1)
            bundle.putInt("currentPostion", mCurrentPostion)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }

        fun showImageViews(context: Context, imageViews: List<ImageView>, postion: Int) {
            if (imageViews == null || imageViews.size == 0) {
                return
            }
            picImageViews = imageViews
            var mCurrentPostion = postion - 1
            if (postion <= 1) {
                mCurrentPostion = 0
            }
            if (postion > picImageViews.size) {
                mCurrentPostion = picImageViews.size - 1
            }
            val intent = Intent(context, PicShowActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("imgType", 2)
            bundle.putInt("currentPostion", mCurrentPostion)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.showpic_activity)
        viewpager = findViewById(R.id.viewpager)
        tvZhishi = findViewById(R.id.tv_zhishi)
        var ivBack = findViewById<ImageView>(R.id.iv_back)
        ivBack.setOnClickListener {
            finish()
        }
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        picBase64Images = listOf()
        picImageViews = listOf()
        picImageUrls = listOf()
    }

    fun initView() {
        val bundle = intent.extras
        if (bundle == null) {
            finish()
            return
        }
        imgType = bundle.getInt("imgType")
        mChoosePoint = bundle.getInt("currentPostion")
        if (imgType == -1) {
            return
        }
        setTvZhishi()
        viewpager.setAdapter(MyShowPicAdapter())
        viewpager.setCurrentItem(mChoosePoint)
        viewpager.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                mChoosePoint = position
                setTvZhishi()
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    inner class MyShowPicAdapter() : PagerAdapter() {
        override fun getCount(): Int {
            if (imgType == 0) {
                return picImageUrls.size
            }
            if (imgType == 1) {
                return picBase64Images.size
            }
            return if (imgType == 2) {
                picImageViews.size
            } else 0
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val photoView = PhotoView(this@PicShowActivity)
            photoView.enable()
            photoView.setScaleType(ImageView.ScaleType.FIT_CENTER)
            photoView.setOnClickListener(View.OnClickListener { finish() })
            val params =
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            if (imgType == 0) {
                Glide.with(this@PicShowActivity).load(picImageUrls.get(position)).into(photoView)
            }
            if (imgType == 1) {
                var base64 = picBase64Images.get(position);
                val input: ByteArray = Base64.decode(base64, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(input, 0, input.size)
                Glide.with(this@PicShowActivity).load(bitmap).into(photoView)
            }
            if (imgType == 2) {
                val iv: ImageView = picImageViews.get(position)
                photoView.setImageDrawable(iv.drawable)
            }
            container.addView(photoView, params)
            return photoView
        }
    }

    private fun setTvZhishi() {
        var totalSize = 0
        if (imgType == 0) {
            totalSize = picImageUrls.size
        }
        if (imgType == 1) {
            totalSize = picBase64Images.size
        }
        if (imgType == 2) {
            totalSize = picImageViews.size
        }
        tvZhishi.setText((mChoosePoint + 1).toString() + "/" + totalSize)
    }
}