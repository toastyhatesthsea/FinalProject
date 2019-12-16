package zafaripour.cs.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class People extends AppCompatActivity {


    private final static String TAG = MainActivity.class.getSimpleName();
    private final static String URL_STRING = "https://ghibliapi.herokuapp.com/people";

    Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        People.DownloadData downloadData = new People.DownloadData(this);
        downloadData.execute(URL_STRING);

        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }



    private static class DownloadData extends AsyncTask<String, Void, String> {

        private WeakReference<People> activityWeakRef;

        DownloadData(People context) {
            activityWeakRef = new WeakReference<>(context);
        }

        @Override
        protected String doInBackground(String... strings) {
            String jsonData = "";
            HttpsURLConnection urlConnection = null;

            try {
                URL aUrl = new URL(strings[0]);
                urlConnection = (HttpsURLConnection) aUrl.openConnection();
                InputStream is = urlConnection.getInputStream();
                Scanner aScanner = new Scanner(is);
                aScanner.useDelimiter("\\A");

                boolean hasInput = aScanner.hasNext();
                if (hasInput) {
                    jsonData = aScanner.next();
                    System.out.println(jsonData);
                } else {
                    return activityWeakRef.get().getResources().
                            getString(R.string.download_error);

                }

            } catch (Exception excep) {
                Log.d(TAG, excep.toString());

            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            StringBuilder results = new StringBuilder();
            String name;
            String age;
            String row;

            try {

                JSONArray jsonArray = new JSONArray(jsonData);
                System.out.println(jsonArray.getJSONObject(0));

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject book = jsonArray.getJSONObject(i);
                    name = book.getString("name");
                    age = book.getString("age");
                    row = name + "\nAge: " + age + "\n\n";

                    results.append(row);
                }
                return results.toString();

            } catch (Exception ex) {
                System.out.println("uhhhhhh");

                Log.d(TAG, ex.toString());
                return activityWeakRef.get().getResources().
                        getString(R.string.data_error);
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            People aActivity = activityWeakRef.get();
            if (aActivity == null || aActivity.isFinishing()) {
                return;
            }
            TextView aTextView = aActivity.findViewById(R.id.peopleTextView);
            aTextView.setText(s);
        }
    }

}
