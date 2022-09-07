package com.zhaopanlong.picshow

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.zhaopanlong.picshowlib.PicShowUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        var iv = findViewById<ImageView>(R.id.iv)

        iv.setOnClickListener {
            PicShowUtils.showUrlImage(
                this, arrayListOf(
                    "https://www.9618968.com/img/imgNew/solution1.jpg",
                    "https://www.9618968.com/img/imgNew/solution2.jpg",
                    "https://www.9618968.com/img/imgNew/solution3.jpg",
                    "https://www.9618968.com/img/imgNew/solution4.jpg",
                    "https://www.9618968.com/img/imgNew/solution5.jpg",
                    "https://www.9618968.com/img/imgNew/solution6.jpg",
                    "https://www.9618968.com/img/imgNew/solution7.jpg",
                    "https://www.9618968.com/img/imgNew/solution8.jpg"
                ),3
            )
        }
    }
}