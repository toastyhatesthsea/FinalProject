package zafaripour.cs.finalproject;

import androidx.annotation.NonNull;

import java.util.Iterator;
import java.util.function.Consumer;

public class QuestionBank implements Iterator {

    public Question[] aQuestions;
    public int currentQuestion;

    public QuestionBank() {
        aQuestions = new StudioGhibliQuestion[]{
                new StudioGhibliQuestion(R.string.question1, true),
                new StudioGhibliQuestion(R.string.question2, false),
                new StudioGhibliQuestion(R.string.question3, true),
        };

        currentQuestion = 0;
    }


    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        Object answer;
        answer = aQuestions[currentQuestion];
        if (currentQuestion == aQuestions.length-1) {
            currentQuestion = 0;
        } else {
            currentQuestion++;
        }
        return answer;
    }

    @Override
    public void remove() {

    }

    @Override
    public void forEachRemaining(@NonNull Consumer action) {

    }
}
