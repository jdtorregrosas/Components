package com.jdtorregrosas.components.questionsCarousel

import android.content.Context
import android.media.Image
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import banlinea.mobile.components.R

/**
 * Created by jdtor on 26.09.2017 for components.
 */
class ViewPagerIndicator @JvmOverloads constructor(
        context: Context,
        size: Int,
        attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private var currentIndex = 0
    private val indicators = mutableListOf<ImageView>()

    init {
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.weight = 0f
        this.layoutParams = layoutParams
        this.orientation = LinearLayout.HORIZONTAL
        this.gravity = Gravity.CENTER
        for(x in 0 until size){
            val indicator = ImageView(context)
            if(x == currentIndex){
                indicator.setImageDrawable(resources.getDrawable(R.drawable.ic_enabled_indicator))
            } else {
                indicator.setImageDrawable(resources.getDrawable(R.drawable.ic_disabled_indicator))
            }
            indicators.add(indicator)
            this.addView(indicator)
        }
    }

    fun moveToNextItem(){
        indicators[currentIndex].setImageDrawable(resources.getDrawable(R.drawable.ic_disabled_indicator))
        if(currentIndex+1 < indicators.size) currentIndex++
        indicators[currentIndex].setImageDrawable(resources.getDrawable(R.drawable.ic_enabled_indicator))
    }

    fun moveToItem(index : Int){
        indicators[currentIndex].setImageDrawable(resources.getDrawable(R.drawable.ic_disabled_indicator))
        if(index < indicators.size) currentIndex = index
        indicators[index].setImageDrawable(resources.getDrawable(R.drawable.ic_enabled_indicator))
    }


}