package zafaripour.cs.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button learnMoreButton, movieButton, peopleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        learnMoreButton = findViewById(R.id.InfoButton);
        movieButton = findViewById(R.id.movieButton);
        peopleButton = findViewById(R.id.peopleButton);

        learnMoreButton.setOnClickListener(new LearnMoreListener());
        movieButton.setOnClickListener(new MovieListener());
        peopleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent peopleScreen = new Intent(getApplicationContext()
                        , People.class);
                startActivity(peopleScreen);
            }
        });
    }


    private class LearnMoreListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Intent learnMoreScreen = new Intent(getApplicationContext(),
                    LearnMore.class);
            startActivity(learnMoreScreen);
        }
    }

    private class MovieListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent movieScreen = new Intent(getApplicationContext(),
                    Movies.class);
            startActivity(movieScreen);
        }
    }

}

