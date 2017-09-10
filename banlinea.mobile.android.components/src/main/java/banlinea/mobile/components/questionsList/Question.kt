package banlinea.mobile.components.questionsList

import android.content.Context
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.*
import banlinea.mobile.components.questionsList.models.Answer
import banlinea.mobile.components.questionsList.models.QuestionType

/**
 * Created by julian on 7/09/17.
 * Question is a private component
 */
class Question(context: Context, type: QuestionType, labels: List<String>, answers: List<Answer> = listOf()) {

    private val linearLayout = LinearLayout(context)
    private var finalAnswer : Answer? = null
    private var isDone = false

    init {
        linearLayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        linearLayout.orientation = LinearLayout.VERTICAL
        // TODO Use random label or something when there are two or more
        val label = AppCompatTextView(context)
        label.text = labels[0]
        linearLayout.addView(label)

        when(type){
            QuestionType.OPEN -> {
                val answerEditText = AppCompatEditText(context)
                answerEditText.inputType = InputType.TYPE_CLASS_TEXT
                answerEditText.addTextChangedListener(object: TextWatcher{
                    override fun afterTextChanged(p0: Editable?) {}
                    override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                    override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        finalAnswer = Answer(1, text.toString())
                        isDone = true
                    }
                })
                linearLayout.addView(answerEditText)
            }
            QuestionType.CLOSED -> {
                val spinner = Spinner(context)
                spinner.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, answers.map { it.value })
                spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(p0: AdapterView<*>?) {}
                    override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                        finalAnswer = answers[position]
                        isDone = true
                    }
                }
                linearLayout.addView(spinner)
            }
        }
    }

    fun getView() : LinearLayout{
        return linearLayout
    }

    fun getAnswer() : Answer? {
        return finalAnswer
    }

    fun isDone() : Boolean {
        return isDone
    }
}