package com.example.bar.coinproj6;


    import android.content.Intent;
    import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

    import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {

        RequestQueue rq;

        TextView   nameText, priceText, changeText;

        String  display_name, price, cap24hrChange;

        String url ="https://coincap.io/page/";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            final Intent intent = getIntent();
            Bundle bd = intent.getExtras();
            if (bd != null) {
                final String symbol = (String) bd.get(valueOf(R.id.pSymboltxt));


            url += symbol;

            rq = Volley.newRequestQueue(this);

            nameText = (TextView) findViewById(R.id.textCoin);
            priceText = (TextView) findViewById(R.id.textPrice);
            changeText = (TextView) findViewById(R.id.priceChange);



            sendjsonrequest();

        }}

        public void sendjsonrequest(){


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        display_name = response.getString("display_name");
                        price = response.getString("price");
                        cap24hrChange = response.getString("cap24hrChange");

                        nameText.setText(display_name);
                        priceText.setText(price);
                        changeText.setText(cap24hrChange);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }

            });

            rq.add(jsonObjectRequest);
        }
    }