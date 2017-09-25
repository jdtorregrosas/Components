# QuestionsList Component
Create dynamically open or close questions

![otp gif preview](./otp.gif)
## Customization
- Error message for mandatory questions
- Questions appearing one after another

## How to use it
1. Import the library component (see library README)
2. Create the component view on the XML file as follows:
```xml
    <com.jdtorregrosas.components.questionsList.QuestionsListView
            android:id="@+id/questionsListView"
            xmlns:questions_list = "http://schemas.android.com/apk/res-auto"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            questions_list:showNextAfterCompletion="true"
            questions_list:mandatoryAnswers="true"
            questions_list:errorMessage="Please answer the question"
            />
```

The table explains each attribute:

|     Attribute           |           Description                      |
| ----------------------- | ------------------------------------------ |
| showNextAfterCompletion | Enable auto appearing questions            |
| mandatoryAnswers        | Enable errors on unanswered question       |
| errorMessage            | Set error message for unanswered questions |

3. Customize what you need with the custom attributes
4. Create your questions list
(Kotlin)
```Kotlin
    /*
    * For each questions it is optional to make it mandatory or to set an error message
    */
    // Create a list and add questions
    val questions = mutableListOf<Question>()
    // If a question must be OPEN, you onbly need the question text
    questions.add(Question(mContext, QuestionType.OPEN, listOf("Are you feeling better today?"), isMandatory = true, errorMessage = "My error Message")))
    // If the question is CLOSED you'll need the list of answers, the placeholder is optional
    //
    questions.add(Question(mContext, QuestionType.CLOSED, listOf("What colour shirt are you wearing?"),
            mutableListOf(Answer(1, "Red"), Answer(2, "Green"), Answer(3, "Blue")), "select a color"))
    questions.add(Question(mContext, QuestionType.OPEN, listOf("Do you like this?")))
    questions.add(Question(mContext, QuestionType.OPEN, listOf("Do you get on well with your boss?")))
    questions.add(Question(mContext, QuestionType.CLOSED, listOf("Where do you live?"),
            mutableListOf(Answer(1, "Bogotá"), Answer(2, "Berlin"))))
    questions.add(Question(mContext, QuestionType.OPEN, listOf("Who will you vote for this election?")))
    questions.add(Question(mContext, QuestionType.CLOSED, listOf("Which food do you prefer?"),
            mutableListOf(Answer(1, "Tomatoes"), Answer(2, "Pasta"), Answer(3, "Tuna"), Answer(2, "Fried chips"))))
    questionsListView.setQuestions(questions)

```

(Java)
```Java
  /*
      * For each questions it is optional to make it mandatory or to set an error message
      */
      // Create a list and add questions
      ArrayList<Question> questions = new ArrayList<Question>()
      // If a question must be OPEN, you onbly need the question text
      questions.add(Question(mContext, QuestionType.OPEN, Arrays.asList("Are you feeling better today?"), isMandatory = true, errorMessage = "My error Message")))
      // If the question is CLOSED you'll need the list of answers, the placeholder is optional
      //
      questions.add(Question(mContext, QuestionType.CLOSED, Arrays.asList("What colour shirt are you wearing?"),
              mutableListOf(Answer(1, "Red"), Answer(2, "Green"), Answer(3, "Blue")), "select a color"))
      questions.add(Question(mContext, QuestionType.OPEN, listOf("Do you like this?")))
      questions.add(Question(mContext, QuestionType.OPEN, listOf("Do you get on well with your boss?")))
      questions.add(Question(mContext, QuestionType.CLOSED, listOf("Where do you live?"),
              mutableListOf(Answer(1, "Bogotá"), Answer(2, "Berlin"))))
      questions.add(Question(mContext, QuestionType.OPEN, listOf("Who will you vote for this election?")))
      questions.add(Question(mContext, QuestionType.CLOSED, listOf("Which food do you prefer?"),
              mutableListOf(Answer(1, "Tomatoes"), Answer(2, "Pasta"), Answer(3, "Tuna"), Answer(2, "Fried chips"))))
      questionsListView.setQuestions(questions)
```


5. To get the code from the inputs you can use the function ```getAnswers():ArrayList<Answer>```

Useful methods:

|     Method              |              Description             |
| ----------------------- | ------------------------------------ |
| setQuestions(questions) | Set an array of questions            |
| getAnswers()            | Retrieve an array of answers         |

6. Enjoy!


