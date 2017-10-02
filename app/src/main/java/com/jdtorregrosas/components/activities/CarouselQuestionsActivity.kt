package com.jdtorregrosas.components.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import banlinea.mobile.components.R
import com.jdtorregrosas.components.questionsCarousel.CarouselItemQuestion
import com.jdtorregrosas.components.questionsCarousel.models.CarouselAnswer
import kotlinx.android.synthetic.main.activity_carousel_questions.*
import org.jetbrains.anko.alert

class CarouselQuestionsActivity : AppCompatActivity() {

    private val mContext: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carousel_questions)
        val question1 = CarouselItemQuestion(mContext, "Complete this phrase. As sick as a...",
                mutableListOf(CarouselAnswer("Penguin", "Penguin"),
                        CarouselAnswer("Parrot", "Parrot"),
                        CarouselAnswer("Puffin", "Puffin"),
                        CarouselAnswer("Partridge", "Partridge")))
        val question2 = CarouselItemQuestion(mContext, "Which legal document states a person's wishes regarding the disposal of their property after death?",
                mutableListOf(CarouselAnswer("Should", "Should"),
                        CarouselAnswer("Will", "Will"),
                        CarouselAnswer("Shall", "Shall"),
                        CarouselAnswer("Would", "Would")))
        val question3 = CarouselItemQuestion(mContext, "Complete the title of the James Bond film The Man With The Golden...",
                mutableListOf(CarouselAnswer("Gun", "Gun"),
                        CarouselAnswer("Tooth", "Tooth"),
                        CarouselAnswer("Delicious", "Delicious"),
                        CarouselAnswer("Eagle", "Eagle"),
                        CarouselAnswer("Treasure", "Treasure"),
                        CarouselAnswer("Foot", "Foot"),
                        CarouselAnswer("Handy", "Handy"),
                        CarouselAnswer("Balls", "Balls"),
                        CarouselAnswer("Finger", "Finger")))

        carouselQuestions.setQuestions(mutableListOf(question1, question2, question3))
        carouselQuestionsButton.setOnClickListener{
            val answers = carouselQuestions.getAnswers()
            var normalizeAnswers = ""
            answers.forEachIndexed{index, answer -> normalizeAnswers += "$index: $answer\n" }
            alert(normalizeAnswers, "Answers:").show()
        }

    }
}
