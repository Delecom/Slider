package com.example.sliderview


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class SliderAdaptor (

    var viewPager : ViewPager2,val imgList:ArrayList<SliderItem>
    ):RecyclerView.Adapter<SliderAdaptor.SliderViewHolder>()
{
   inner class SliderViewHolder(var v:View): RecyclerView.ViewHolder(v){
       val img = v.findViewById<ImageView>(R.id.imageSlider)
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
       val inflater= LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.slider_image,parent,false)
        return SliderViewHolder(v)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
       val listImg = imgList[position]
        holder.img.setImageResource(listImg.img)
        if (position == imgList.size -2){
            viewPager.post(run)
        }

    }

    private val run = object : Runnable{
        @SuppressLint("NotifyDataSetChanged")
        override fun run() {
            imgList.addAll(imgList)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int  = imgList.size


}