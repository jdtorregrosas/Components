package com.jdtorregrosas.components.questionsCarousel

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import com.jdtorregrosas.components.questionsCarousel.adapters.QuestionsPagerAdapter


/**
 * Created by jdtor on 25.09.2017 for components.
 * Carousel view pager
 */
class CarouselQuestionsView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null
) : ViewPager(context, attrs){

    init {
        this.setPadding(20,20,20,20)
    }

    fun setQuestions(questions: MutableList<CarouselItemQuestion>){
        this.adapter = QuestionsPagerAdapter(context, questions)
    }
}


