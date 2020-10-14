package com.spreadinghands.covid_tracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.graphics.Color.WHITE;

public class Statistics extends AppCompatActivity {

    ImageView back;
    TextView tvcases,tvrecovered,tvdeath,tvactive,tvserious;
    Button button4,button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        back = findViewById(R.id.imageView);
        tvcases = findViewById(R.id.textView12);
        tvrecovered = findViewById(R.id.textView18);
        tvdeath = findViewById(R.id.textView14);
        tvactive = findViewById(R.id.textView16);
        tvserious = findViewById(R.id.textView20);
        button4 = findViewById(R.id.button4);
        button3 = findViewById(R.id.button3);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i =new Intent(Statistics.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if(button4.isPressed()) {

                button4.setBackground(getResources().getDrawable(R.drawable.spinner_bg));
                button4.setTextColor(Color.BLACK);
                button3.setBackground(getResources().getDrawable(R.drawable.statistic_button));
                button3.setTextColor(Color.WHITE);

            }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(button3.isPressed()) {

                    button4.setBackground(getResources().getDrawable(R.drawable.statistic_button));
                    button4.setTextColor(Color.WHITE);
                    button3.setBackground(getResources().getDrawable(R.drawable.spinner_bg));
                    button3.setTextColor(Color.BLACK);

                }
            }
        });

        fetchdata();
    }

    private void fetchdata() {
        final ProgressDialog pd=new ProgressDialog(Statistics.this);

        String url = "https://corona.lmao.ninja/v2/all/";

        StringRequest request = new StringRequest(Request.Method.GET, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject jsonObject  = new JSONObject(response.toString());
                    tvcases.setText(jsonObject.getString("cases"));
                    tvdeath.setText(jsonObject.getString("deaths"));
                    tvrecovered.setText(jsonObject.getString("recovered"));
                    tvactive.setText(jsonObject.getString("active"));
                    tvserious.setText(jsonObject.getString("critical"));


                }
                catch (JSONException e) {
                e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Statistics.this,error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


}