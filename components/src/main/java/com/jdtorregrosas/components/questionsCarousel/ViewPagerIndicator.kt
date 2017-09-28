package com.jdtorregrosas.components.questionsCarousel

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import banlinea.mobile.components.R

/**
 * Created by jdtor on 26.09.2017 for components.
 * Carousel indicator
 */
class ViewPagerIndicator @JvmOverloads constructor(
        context: Context,
        size: Int,
        var enableIndicatorDrawable: Drawable? = null,
        var disableIndicatorDrawable: Drawable? = null,
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
        if(enableIndicatorDrawable == null){
            enableIndicatorDrawable = ContextCompat.getDrawable(context, R.drawable.ic_enabled_indicator)
        }
        if(disableIndicatorDrawable == null){
            disableIndicatorDrawable = ContextCompat.getDrawable(context, R.drawable.ic_disabled_indicator)
        }
        for(x in 0 until size){
            val indicator = ImageView(context)
            if(x == currentIndex){
                enableIndicator(indicator)
            } else {
                disableIndicator(indicator)
            }
            indicators.add(indicator)
            this.addView(indicator)
        }
    }

    fun moveToNextItem(){
        disableIndicator(indicators[currentIndex])
        if(currentIndex+1 < indicators.size) currentIndex++
        enableIndicator(indicators[currentIndex])
    }

    fun moveToItem(index : Int){
        disableIndicator(indicators[currentIndex])
        if(index < indicators.size) currentIndex = index
        enableIndicator(indicators[index])
    }

    private fun enableIndicator(indicator: ImageView){
        indicator.setImageDrawable(enableIndicatorDrawable)
    }

    private fun disableIndicator(indicator: ImageView){
        indicator.setImageDrawable(disableIndicatorDrawable)
    }

}