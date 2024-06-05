package ders.android.jsoupapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    Button btnAnns,btnNews,btnLinks,btnOffices;
    TextView tvTitle;
    ListView lvList;
    String URL="https://www.karabuk.edu.tr";
    Document doc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAnns=findViewById(R.id.btn_Anns);
        btnNews=findViewById(R.id.btn_News);
        btnLinks=findViewById(R.id.btn_Links);
        btnOffices=findViewById(R.id.btn_Offices);
        tvTitle=findViewById(R.id.tv_Title);
        lvList=findViewById(R.id.lv_List);
        btnOffices.setOnClickListener(this);
        btnLinks.setOnClickListener(this);
        btnNews.setOnClickListener(this);
        btnAnns.setOnClickListener(this);
        RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
        StringRequest request=new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        doc=Jsoup.parse(response);
                        Toast.makeText(getApplicationContext(),"Downloading completed succesfully",Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Error",error.getMessage());
                }
        });
        queue.add(request);
    }

    @Override
    public void onClick(View view) {
        int Id=view.getId();
        ArrayList<String> list=new ArrayList<>();
        if(Id==R.id.btn_Anns){
            tvTitle.setText("Duyurular");
            Element parent=doc.getElementById("KbuDuyuru");
            Elements children=parent.children();
            for(Element child:children) {
                if(child.className()=="owl-stage-outer"){
                    Element stage=child.child(0);
                    Element div=stage.child(2);
                    Element item=div.child(0);
                    for(Element a:item.children()){
                        Element i=a.child(0);
                        Element l=i.child(0);
                        Element span=l.select("span").first();
                        list.add(span.text());
                    }
                }
            }
        }
        if(Id==R.id.btn_News){
            tvTitle.setText("Habeler");
            Element parent=doc.getElementById("KbuHaber");
            Element outer=parent.child(0);
            Element stage=outer.child(0);
            for(Element div:stage.children()) {
                Element haber=div.child(0).child(0);
                list.add(haber.select("img").attr("alt"));
            }
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,list);
        lvList.setAdapter(adapter);
    }
}