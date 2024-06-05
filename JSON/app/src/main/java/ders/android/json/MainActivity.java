package ders.android.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView tvTitle;
    ImageView ivLogo;
    ListView lvCompanies;
    String URL="http://vrarch.org/mobil_ders/recyle.json";
    String [] companyInfos;
    String [] imageURLs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTitle=findViewById(R.id.tv_Title);
        ivLogo=findViewById(R.id.iv_Logo);
        lvCompanies=findViewById(R.id.lv_Companies);
        lvCompanies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Picasso.get().load(imageURLs[position]).into(ivLogo);
            }
        });

        RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
        StringRequest request=new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object=new JSONObject(response);
                            JSONArray companies=object.getJSONArray("Companies");
                            companyInfos=new String[companies.length()];
                            imageURLs=new String[companies.length()];
                            for(int i=0;i<companies.length(); i++){
                                JSONObject company= companies.getJSONObject(i);
                                String name=company.getString("Heading");
                                String slogan=company.getString("Detail");
                                companyInfos[i]=name+"-"+slogan;
                                imageURLs[i]=company.getString("ImageURL");
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,companyInfos);
                        lvCompanies.setAdapter(adapter);
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