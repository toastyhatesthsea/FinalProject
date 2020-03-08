package zafaripour.cs.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class QuizThing extends AppCompatActivity {
    private static final String TAG = QuizThing.class.getSimpleName();
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button gameOverButton;
    private TextView questionTextView;
    private TextView answerTextView;
    private TextView gameScoreTextView;
    private TextView lifetimeScoreTextView;

    private QuestionBank bankOfQuestions;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_layout);

        bankOfQuestions = new QuestionBank();

        Intent send = getIntent();

        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);
        nextButton = findViewById(R.id.nextButton);

        questionTextView = findViewById(R.id.questionTextView);
        answerTextView = findViewById(R.id.answerTextView);

        trueButton.setOnClickListener(new TrueButtonClickListener());
        falseButton.setOnClickListener(new FalseButtonClickListener());

        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new NextButtonClickListener());

        updateQuestion();

    }


    private void updateQuestion() {
        StudioGhibliQuestion aQuestion = (StudioGhibliQuestion) bankOfQuestions.next();
        questionTextView.setText(aQuestion.textResId);

    }

    private void checkAnswer(Boolean userPress) {
        boolean correctAnswer = bankOfQuestions.aQuestions[bankOfQuestions.currentQuestion].isAnswerTrue();


        if (userPress == correctAnswer) {
            answerTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
            answerTextView.setText(getResources().getText(R.string.correct));

        } else {

            answerTextView.setText(getResources().getText(R.string.incorrect));
        }

        answerTextView.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.VISIBLE);

    }

    private class TrueButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            checkAnswer(true);
            v.setEnabled(false);
            falseButton.setVisibility(View.GONE);

        }
    }

    private class FalseButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            checkAnswer(false);
            v.setEnabled(false);
            trueButton.setVisibility(View.GONE);

        }
    }

    private class NextButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            updateQuestion();
            answerTextView.setVisibility(View.INVISIBLE);
            nextButton.setVisibility(View.GONE);
            trueButton.setVisibility(View.VISIBLE);
            falseButton.setVisibility(View.VISIBLE);
            trueButton.setEnabled(true);
            falseButton.setEnabled(true);
        }
    }
}