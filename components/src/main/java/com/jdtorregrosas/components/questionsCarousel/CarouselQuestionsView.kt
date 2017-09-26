package com.jdtorregrosas.components.questionsCarousel

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.widget.LinearLayout
import banlinea.mobile.components.R
import com.jdtorregrosas.components.questionsCarousel.adapters.QuestionsPagerAdapter


/**
 * Created by jdtor on 25.09.2017 for components.
 * Carousel view pager
 */
class CarouselQuestionsView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null
) : LinearLayout(context, attrs){

    private lateinit var viewPager : ViewPager
    private lateinit var viewPagerIndicator : ViewPagerIndicator
    private var isNavigationDisabled = false
    init {
        this.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        this.orientation = LinearLayout.VERTICAL
        this.setPadding(20,20,20,20)
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CarouselQuestionsView)
            isNavigationDisabled = typedArray.getBoolean(R.styleable.CarouselQuestionsView_disableNavigation, false)
            typedArray.recycle()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun setQuestions(questions: MutableList<CarouselItemQuestion>){
        viewPager = if(isNavigationDisabled) NonSwipeableViewPager(context) else ViewPager(context)
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        layoutParams.weight = 1f
        viewPager.layoutParams = layoutParams
        viewPager.adapter = QuestionsPagerAdapter(context, questions)
        viewPagerIndicator = ViewPagerIndicator(context, viewPager.adapter.count)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                viewPagerIndicator.moveToItem(position)
            }

        })
        questions.forEach {
            it.setOnClickListener{
                setNextCurrentItem(true)
            }
        }
        this.addView(viewPager)
        this.addView(viewPagerIndicator)
    }

    private fun setNextCurrentItem(smoothScroll: Boolean) {
        viewPager.setCurrentItem(viewPager.currentItem+1, smoothScroll)
        viewPagerIndicator.moveToItem(viewPager.currentItem)
    }
}


