package banlinea.mobile.components.questionsList

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.ScrollView

/**
 * Created by jdtor on 09.09.2017 for components.
 */
class QuestionsListView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0,
        defStyleRes: Int = 0
) : ScrollView(context, attrs, defStyle, defStyleRes) {
    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }
    fun setQuestions(questions : List<Question>){
        val linearLayout = LinearLayout(context)
        linearLayout.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        linearLayout.orientation = LinearLayout.VERTICAL
        for(question in questions){
            linearLayout.addView(question.getView())
        }
        this.addView(linearLayout)
    }
}