package com.prices.products.productsprices;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class DashboardActivity extends AppCompatActivity {

    private TextView textViewNome;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        JSONObject jsonObject = null;

        textViewNome = (TextView) findViewById(R.id.textViewNome);

        try {
            token = getIntent().getStringExtra("token");
            jsonObject = new JSONObject(JWTUtils.decoded(token));
            textViewNome.setText(jsonObject.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
