package zafaripour.cs.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button learnMoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        learnMoreButton = findViewById(R.id.LearnMore);
    }


    private class LearnMoreListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Intent learnMoreScreen = new Intent(getApplicationContext(), )

        }
    }
}

