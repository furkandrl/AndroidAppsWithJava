package com.example.odev9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{
    String URL="http://vrarch.org/en";
    ListView listView_headline;
    Document doc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listView_headline = findViewById(R.id.listView_headline);
        RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
        StringRequest request=new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        doc= Jsoup.parse(response);
                        Toast.makeText(getApplicationContext(),"Downloading completed succesfully",Toast.LENGTH_SHORT).show();
                        if (doc == null) {

                            Toast.makeText(getApplicationContext(),"Failed to parse HTML.",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        ArrayList<String> list=new ArrayList<>();



                        Elements h6Elements = doc.select("h6");

                        // Iterate through each <h6> element
                        for (Element h6Element : h6Elements) {
                            // Select <a> tag within <h6> element
                            Element aElement = h6Element.selectFirst("a");
                            if (aElement != null) {
                                // Get the text inside <a> tag
                                list.add(aElement.text());

                            }
                        }

                        ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,list);
                        listView_headline.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.getMessage());
            }
        });
        queue.add(request);




    }


}