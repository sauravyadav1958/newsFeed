package com.example.newsfeed;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {
    private CustomAdapter customAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Adapter: bridge between UI component and data source
        customAdapter = new CustomAdapter();
        fetchData();
        recyclerView.setAdapter(customAdapter);
    }

    private void fetchData() {
        String date = getOneMonthBackDate();
        String url = "https://newsapi.org/v2/everything?q=tesla&from="+ date + "&sortBy=publishedAt&apiKey=b0ad722513a44a16a3aebd9e3f3decca";
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        try {
                            JSONArray jsonArray = response.getJSONArray("articles");
                            List<News> newsList = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                News news = new News(
                                        jsonObject.getString("author"),
                                        jsonObject.getString("title"),
                                        jsonObject.getString("url"),
                                        jsonObject.getString("urlToImage")
                                );
                                newsList.add(news);
                            }
                            customAdapter.updateNews(newsList);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }

        ) {
            // This will prevent 403 exception for api
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("User-Agent", "Mozilla/5.0");

                return headers;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(getRequest);
    }

    private String getOneMonthBackDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        date.setMonth(date.getMonth()-1);
        String dateString = formatter.format(date);
        return dateString;
    }


}
