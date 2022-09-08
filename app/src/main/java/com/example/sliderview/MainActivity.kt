package com.example.sliderview

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class MainActivity(private val viewPagerImgSlider: ViewPager2) : AppCompatActivity() {

    private lateinit var sliderItemList: ArrayList<SliderItem>
    private lateinit var sliderAdaptor:SliderAdaptor
    private lateinit var sliderHandle:Handler
    private lateinit var sliderRun:Runnable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sliderItem()
        itemSliderView()
    }

    private fun sliderItem() {
        sliderItemList = ArrayList()
        sliderAdaptor = SliderAdaptor(viewPagerImgSlider,sliderItemList)
        viewPagerImgSlider.adapter = sliderAdaptor
        viewPagerImgSlider.clipToPadding = false
        viewPagerImgSlider.clipChildren = false
        viewPagerImgSlider.offscreenPageLimit = 3
        viewPagerImgSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val comPosPageTarn = CompositePageTransformer()
        comPosPageTarn.addTransformer(MarginPageTransformer(40))
        comPosPageTarn.addTransformer{page,position ->
            val r :Float = 1  - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        viewPagerImgSlider.setPageTransformer(comPosPageTarn)
        sliderHandle = Handler(Looper.myLooper()!!)
        sliderRun = Runnable {
            viewPagerImgSlider.currentItem = viewPagerImgSlider.currentItem + 1
        }
        viewPagerImgSlider.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderHandle.removeCallbacks(sliderRun)
                    sliderHandle.postDelayed(sliderRun,2000)
                }
            }
        )


    }

    override fun onPause() {
        super.onPause()
        sliderHandle.removeCallbacks(sliderRun)

    }

    override fun onResume() {
        super.onResume()
        sliderHandle.postDelayed(sliderRun,2000)
    }

    private fun itemSliderView() {
        sliderItemList.add(SliderItem(R.drawable.image1))
        sliderItemList.add(SliderItem(R.drawable.image10))
        sliderItemList.add(SliderItem(R.drawable.image11))
        sliderItemList.add(SliderItem(R.drawable.image2))
        sliderItemList.add(SliderItem(R.drawable.image9))
        sliderItemList.add(SliderItem(R.drawable.image7))
        sliderItemList.add(SliderItem(R.drawable.image5))
        sliderItemList.add(SliderItem(R.drawable.image4))
        sliderItemList.add(SliderItem(R.drawable.image3))
        sliderItemList.add(SliderItem(R.drawable.image6))
    }


}