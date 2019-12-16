package zafaripour.cs.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class Movies extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();
    private final static String URL_STRING = "https://ghibliapi.herokuapp.com/films";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);


    }

    public void goButtonClick(View v) {
        DownloadData downloadData = new DownloadData(this);
        downloadData.execute(URL_STRING);
    }

    private static class DownloadData extends AsyncTask<String, Void, String> {

        private WeakReference<Movies> activityWeakRef;

        DownloadData(Movies context) {
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
            } catch (Exception excep) {
                Log.d(TAG, excep.toString());
            }
            finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }


            return null;
        }
    }

}
