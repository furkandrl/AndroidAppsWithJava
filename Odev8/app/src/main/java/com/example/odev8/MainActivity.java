package com.example.odev8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Spinner teacherSpinner;
    private ListView classListView;
    List<String> teachers;
    List<String> classNames;
    String URL = "http://vrarch.org/mobil_ders/school.json";
    Map<String, JSONObject> teacherMap = new HashMap<>();
    Map<String, JSONObject> classMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teachers = new ArrayList<>();
        classNames = new ArrayList<>();
        teacherSpinner = findViewById(R.id.spinner1);
        classListView = findViewById(R.id.listView1);

        ArrayAdapter<String> classAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        classListView.setAdapter(classAdapter);

        classListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String className = (String) classListView.getItemAtPosition(position);
                JSONObject classSelected = classMap.get(className);
                try {
                    String code = classSelected.getString("Kodu");
                    int credits = classSelected.getInt("Kredisi");
                    Toast.makeText(MainActivity.this, code+", "+className +", "+credits, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        fetchData();
    }

    private void fetchData(){
        RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
        StringRequest request=new StringRequest(Request.Method.GET, URL,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object=new JSONObject(response);
                        JSONArray teachersJSON=object.getJSONArray("OgretimElemanlari");

                        for(int i=0;i<teachersJSON.length(); i++){
                            JSONObject teacher= teachersJSON.getJSONObject(i);
                            String name=teacher.getString("adi");
                            teacherMap.put(name, teacher);

                            teachers.add(name);
                        }

                        teacherSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String selectedTeacher = (String) parent.getItemAtPosition(position);
                                int sicilNo=0;
                                try {
                                    sicilNo = teacherMap.get(selectedTeacher).getInt("sicil");
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                                fetchClasses(sicilNo);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                // Handle nothing selected
                            }
                        });
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,teachers);
                    teacherSpinner.setAdapter(adapter);
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", error.getMessage());
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        queue.add(request);
    }

    private void fetchClasses(int sicil){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.GET, URL,
            new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object=new JSONObject(response);
                        JSONArray derslerJSON =object.getJSONArray("Dersler");
                        classNames.clear();
                        for(int i = 0; i< derslerJSON.length(); i++){
                            JSONObject ders= derslerJSON.getJSONObject(i);
                            int sicilInDers=ders.getInt("OgretmenSicil");
                            String name = ders.getString("Adi");

                            if(sicilInDers == sicil){
                               classNames.add(name);
                               classMap.put(name, ders);
                            }
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,classNames);
                    classListView.setAdapter(adapter);
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", error.getMessage());
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        queue.add(request);


    }

}