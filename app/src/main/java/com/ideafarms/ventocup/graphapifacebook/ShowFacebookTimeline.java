package com.ideafarms.ventocup.graphapifacebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ideafarms.ventocup.graphapifacebook.adapter.FacebookTImeLineAdapter;
import com.ideafarms.ventocup.graphapifacebook.model.FacebookPage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowFacebookTimeline extends AppCompatActivity {

    private static final String TAG = ShowFacebookTimeline.class.getSimpleName();
    private String mFacebookResponse;
    JSONObject mFacebookJsonObject;
    List<FacebookPage> _mFacebookPost;
    RecyclerView mRecyclerView;
    Context mContext;
    FacebookTImeLineAdapter mFacebookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_facebook_timeline);
        mContext = ShowFacebookTimeline.this;
        mRecyclerView = (RecyclerView) findViewById(R.id.rvFacebookTweets);

        _mFacebookPost = new ArrayList<FacebookPage>();


        Intent getFacebookIntent = getIntent();

        mFacebookResponse = getFacebookIntent.getStringExtra("response");
        Log.e(TAG, mFacebookResponse);

        try {
            mFacebookJsonObject = new JSONObject(mFacebookResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        parseFacebookJsonData(mFacebookJsonObject);


    }

    private void parseFacebookJsonData(JSONObject mFacebookJsonObject) {
        try {
            JSONObject posts = mFacebookJsonObject.getJSONObject("posts");
            JSONArray data = posts.getJSONArray("data");
            Log.e(TAG, data.toString());

            mRecyclerView.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            List<FacebookPage> _test = new ArrayList<FacebookPage>();
            _test = getPostFromArray(data);
            mFacebookAdapter = new FacebookTImeLineAdapter(getPostFromArray(data), mContext);
            mRecyclerView.setAdapter(mFacebookAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private List<FacebookPage> getPostFromArray(JSONArray data) throws JSONException {

        HashMap<String, String> _postData = new HashMap<String, String>();


        for (int i = 0; i < data.length(); i++) {
            FacebookPage mFacebook = new FacebookPage();

            JSONObject post = (JSONObject) data.get(i);
//            Log.e(TAG, post.get("message").toString());

            if (post.has("message")) {
                _postData.put("message", post.get("message").toString());
                mFacebook.setMessage(post.get("message").toString());
            } else {
                _postData.put("message", null);
                mFacebook.setMessage(null);
            }
            if (post.has("created_time")) {
                _postData.put("created_time", post.get("created_time").toString());
                mFacebook.setCreated_time(post.get("created_time").toString());
            } else {
                _postData.put("created_time", null);
                mFacebook.setCreated_time(null);
            }
            if (post.has("picture")) {
                _postData.put("picture", post.get("picture").toString());
                mFacebook.setPicture(post.get("picture").toString());
            } else {
                _postData.put("picture", null);
                mFacebook.setPicture(null);
            }

            if (post.has("full_picture")) {
                _postData.put("full_picture", post.get("full_picture").toString());
                mFacebook.setFull_picture(post.get("full_picture").toString());
            } else {
                _postData.put("full_picture", null);
                mFacebook.setFull_picture(null);

            }
            if (post.has("id")) {
                _postData.put("id", post.get("id").toString());
                mFacebook.setId(post.get("id").toString());
            } else {
                _postData.put("id", null);
                mFacebook.setId(null);
            }
            _mFacebookPost.add(mFacebook);
        }
        return _mFacebookPost;
    }

}
