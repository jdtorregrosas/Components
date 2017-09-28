package com.jdtorregrosas.components.questionsCarousel.adapters

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.jdtorregrosas.components.questionsCarousel.CarouselItemQuestion

/**
 * Created by jdtor on 25.09.2017 for components.
 * View pager adapter
 */
class QuestionsPagerAdapter(val context: Context, private val questions: MutableList<CarouselItemQuestion>) : PagerAdapter() {

    override fun getCount(): Int {
        return questions.size
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun getItemPosition(`object`: Any?): Int {
        return questions.indexOf(`object`)
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        val layout : ViewGroup = questions[position]
        container?.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(`object` as View)
    }

}