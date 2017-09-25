package com.jdtorregrosas.components.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import banlinea.mobile.components.R
import com.jdtorregrosas.components.questionsCarousel.CarouselAnswer
import com.jdtorregrosas.components.questionsCarousel.CarouselItemQuestion
import kotlinx.android.synthetic.main.activity_carousel_questions.*

class CarouselQuestionsActivity : AppCompatActivity() {

    private val mContext: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carousel_questions)
        val question1 = CarouselItemQuestion(mContext, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                mutableListOf(CarouselAnswer("test", "test"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2")))
        val question2 = CarouselItemQuestion(mContext, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                mutableListOf(CarouselAnswer("test", "test"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2")))
        val question3 = CarouselItemQuestion(mContext, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.   \n" +
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
        val question4 = CarouselItemQuestion(mContext, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                mutableListOf(CarouselAnswer("test", "test"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2")))
        val question5 = CarouselItemQuestion(mContext, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                mutableListOf(CarouselAnswer("test", "test"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2")))
        val question6 = CarouselItemQuestion(mContext, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                mutableListOf(CarouselAnswer("test", "test"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2"),
                        CarouselAnswer("test2", "test2")))

        carouselQuestions.setQuestions(mutableListOf(question1, question2, question3, question4, question5, question6))

    }
}
