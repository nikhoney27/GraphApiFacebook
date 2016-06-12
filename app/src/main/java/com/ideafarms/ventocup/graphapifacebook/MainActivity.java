package com.ideafarms.ventocup.graphapifacebook;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button btnGetPost;
    private GetPost mGetPost;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;

        btnGetPost = (Button) findViewById(R.id.btnGetPost);

        btnGetPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetPost = new GetPost();
                mGetPost.execute();
            }
        });


    }


    class GetPost extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String getPo = getFacebookPost();
            return getPo;
        }

        @Override
        protected void onPostExecute(String s) {
            Intent shareIntent = new Intent(mContext, ShowFacebookTimeline.class);
            shareIntent.putExtra("response", s);
            startActivity(shareIntent);
            super.onPostExecute(s);

        }
    }

    private String getFacebookPost() {
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL("https://graph.facebook.com/218228771636030/?fields=posts{message,created_time,picture,full_picture}&&access_token=1100093410050929|J_4DfEzMfHEhERntzg-Nj3V4zz0");
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader ins =
                    new BufferedReader(new InputStreamReader(url.openStream()));

            String response;

            while ((response = ins.readLine()) != null) {
                builder.append(response);
                Log.e(TAG, response);
                return response;
            }


//            readStream(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}