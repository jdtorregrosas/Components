package com.jdtorregrosas.components.questionsList

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.text.Editable
import android.text.InputType
import android.text.SpannableString
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import com.jdtorregrosas.components.questionsList.models.Answer
import com.jdtorregrosas.components.questionsList.models.QuestionType


/**
 * Created by julian on 7/09/17.
 * Question is a private component
 */
class Question(private val context: Context, private val type: QuestionType, private var labels: List<String>, private var answers: MutableList<Answer> = mutableListOf(), var spinnerPlaceholder: String = "Select an answer", var isMandatory: Boolean = false, var errorMessage: String = "Answer the question") {

    private val linearLayout = LinearLayout(context)
    private var finalAnswer : Answer? = null
    private var isAnswered = false
    private var onAnsweredListener : () -> Unit = {}

    init {}

    fun createQuestion(){
        linearLayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.setPadding(0,15,0,15)
        // TODO Use random label or something when there are two or more
        val label = AppCompatTextView(context)
        label.text = SpannableString("${labels[0]} ${if(isMandatory)"(*)" else ""}")
        linearLayout.addView(label)

        val errorLabel = AppCompatTextView(context)
        errorLabel.text = errorMessage
        errorLabel.setTextColor(Color.RED)

        when(type){
            QuestionType.OPEN -> {
                val answerEditText = AppCompatEditText(context)
                answerEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
                answerEditText.addTextChangedListener(object: TextWatcher{
                    override fun afterTextChanged(p0: Editable?) {}
                    override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                    override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        finalAnswer = Answer(1, text.toString())
                        onAnsweredListener()
                        isAnswered = true
                        if(text.isNullOrBlank() && isMandatory){
                            errorLabel.visibility = View.VISIBLE
                        } else {
                            errorLabel.visibility = View.GONE
                        }
                    }
                })
                linearLayout.addView(answerEditText)
            }
            QuestionType.CLOSED -> {
                val spinner = Spinner(context)
                answers.add(0, Answer(0, spinnerPlaceholder))
                spinner.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, answers.map { it.value })
                spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        if(isMandatory){
                            errorLabel.visibility = View.VISIBLE
                        } else {
                            errorLabel.visibility = View.GONE
                        }
                    }
                    override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                        when {
                            position != 0 -> {
                                finalAnswer = answers[position]
                                onAnsweredListener()
                                isAnswered = true
                                errorLabel.visibility = View.GONE
                            }
                            isMandatory && isAnswered -> {
                                errorLabel.visibility = View.VISIBLE
                                isAnswered = false
                            }
                        }
                    }
                }
                linearLayout.addView(spinner)
            }
        }
        if(isMandatory){
            linearLayout.addView(errorLabel)
            errorLabel.visibility = View.GONE
        }
    }

    fun getView() : LinearLayout{
        return linearLayout
    }

    fun setOnAnsweredListener(listener: () -> Unit){
        onAnsweredListener = listener
    }

    fun getAnswer() : Answer? {
        return finalAnswer
    }

    fun isAnswered() : Boolean {
        return isAnswered
    }
}