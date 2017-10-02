# QuestionsCarousel Component
Create a questions carousel with your customized colors and indicator

![questionsCarousel gif preview](./questionsCarousel.gif)
## Customization
- Labels color
- Buttons text color
- Buttons background color
- Indicator enabled/disabled images
- Navigation between questions
- Space between fields

## How to use it
1. Import the library component (see library README)
2. Create the component view on the XML file as follows:
```xml
    <com.jdtorregrosas.components.questionsCarousel.CarouselQuestionsView
        xmlns:carouselQuestions = "http://schemas.android.com/apk/res-auto"
        android:id="@+id/carouselQuestions"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        carouselQuestions:disableNavigation="false"
        carouselQuestions:buttonTextColor="@color/buttonTextColor"/>
        carouselQuestions:buttonColor="@color/colorPrimaryDark"
        carouselQuestions:enableIndicatorDrawable="@drawable/ic_custom_indicator_enabled"
        carouselQuestions:disableIndicatorDrawable="@drawable/ic_custom_indicator_disabled"
        carouselQuestions:labelColor="@color/labelColor"
```

The table explains each attribute:

|     Attribute                |           Description                       |
| ---------------------------- | ------------------------------------------- |
| disableNavigation            | disables navigation between questions       |
| buttonTextColor              | Sets the button text color                  |
| buttonColor                  | Sets the button background color of fields  |
| enableIndicatorDrawable      | Sets the enabled indicator image            |
| disableIndicatorDrawable     | Sets the disabled indicator image           |

3. Customize what you need with the custom attributes
4. In order to set the questions you'll have to do it programmatically:

(Kotlin)
```kotlin
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
```
(java)
```java
         CarouselItemQuestion question1 = new CarouselItemQuestion(mContext, "Complete this phrase. As sick as a...",
                new ArrayList<CarouselAnswer>(){{
                    add(new CarouselAnswer("Penguin", "Penguin"));
                    add(new CarouselAnswer("Parrot", "Parrot"));
                    add(new CarouselAnswer("Puffin", "Puffin"));
                    add(new CarouselAnswer("Partridge", "Partridge"));
                }}
         CarouselItemQuestion question2 = new CarouselItemQuestion(mContext, "Which legal document states a person's wishes regarding the disposal of their property after death?",
                new ArrayList<CarouselAnswer>(){{
                  add(new CarouselAnswer("Should", "Should"));
                  add(new CarouselAnswer("Will", "Will"));
                  add(new CarouselAnswer("Shall", "Shall"));
                  add(new CarouselAnswer("Would", "Would"));
                }}

         carouselQuestions.setQuestions(mutableListOf(question1, question2))
```

5. To get the answers use: ```getAnswers():List<String>```

6. Enjoy!


