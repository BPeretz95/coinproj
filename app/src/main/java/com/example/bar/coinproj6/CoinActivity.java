package com.example.bar.coinproj6;

/**
 * Created by Bar Peretz on 4/17/2018.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CoinActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    List<DataCoin> DataCoinList;

    RequestQueue rq;

    String request_url = "http://coincap.io/front";

    public CoinActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coinpickactivity);

        rq = Volley.newRequestQueue(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycleViewContainer);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        DataCoinList = new ArrayList<>();

        sendRequest();

    }



    public void sendRequest(){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, request_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i = 0; i < response.length(); i++){

                    DataCoin dataCoin = new DataCoin();

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        dataCoin.setcoinName(jsonObject.getString("long"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    DataCoinList.add(dataCoin);

                }

                mAdapter = new AdapterCoin(CoinActivity.this, DataCoinList) {

                };

                recyclerView.setAdapter(mAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Volley Error: ", String.valueOf(error));
            }
        });

        rq.add(jsonArrayRequest);

    }


    }







