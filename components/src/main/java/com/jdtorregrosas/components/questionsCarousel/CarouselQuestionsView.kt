package com.jdtorregrosas.components.questionsCarousel

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.ViewGroup
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import banlinea.mobile.components.R
import com.jdtorregrosas.components.questionsList.Question


/**
 * Created by jdtor on 25.09.2017 for components.
 */
class CarouselQuestionsView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null
) : ViewPager(context, attrs){

    init {
        val question1 = CarouselItemQuestion(context, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                mutableListOf(CarouselAnswer("test", "test"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2")))
        val question2 = CarouselItemQuestion(context, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                mutableListOf(CarouselAnswer("test", "test"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2")))
        val question3 = CarouselItemQuestion(context, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.   \n" +
                "\n" +
                "Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet,",
                mutableListOf(CarouselAnswer("test", "test"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2")))
        val question4 = CarouselItemQuestion(context, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                mutableListOf(CarouselAnswer("test", "test"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2")))
        val question5 = CarouselItemQuestion(context, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                mutableListOf(CarouselAnswer("test", "test"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2")))
        val question6 = CarouselItemQuestion(context, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                mutableListOf(CarouselAnswer("test", "test"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2")))
        this.adapter = QuestionsPagerAdapter(context, mutableListOf(question1, question2, question3, question4, question5, question6))
        this.setPadding(20,20,20,20)
    }


}

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
        val layout : ViewGroup = questions[position].getView()
        container?.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(`object` as View)
    }

}
