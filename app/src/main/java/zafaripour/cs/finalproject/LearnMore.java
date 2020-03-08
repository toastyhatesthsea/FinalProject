package zafaripour.cs.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LearnMore extends AppCompatActivity {

    TextView info;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_learn_more);

        info = findViewById(R.id.learnMore);
        backButton = findViewById(R.id.backButton);

        info.setText(R.string.info);

        backButton.setOnClickListener(new BackButtonListener());
    }


    private class BackButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent backButtonScreen = new Intent(getApplicationContext(),
                    MainActivity.class);
            startActivity(backButtonScreen);
        }
    }
}

