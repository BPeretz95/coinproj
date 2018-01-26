package com.example.bar.coinproj6;

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

public class MainActivity extends AppCompatActivity {

    RequestQueue rq;

    TextView   fbURLText, youtubeURLText, googlePlusURLText, nameText, priceText;

    String  facebookURL, youtubeURL, gplusURL, display_name, price;

    String url ="https://coincap.io/page/BTC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rq = Volley.newRequestQueue(this);

        nameText = (TextView) findViewById(R.id.textCoin);
        priceText = (TextView) findViewById(R.id.textPrice);
        fbURLText = (TextView) findViewById(R.id.textfacebookurl);
        youtubeURLText = (TextView) findViewById(R.id.textyoutubeurl);
        googlePlusURLText = findViewById(R.id.textgplusurl);

        sendjsonrequest();

    }

    public void sendjsonrequest(){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    display_name = response.getString("display_name");
                    price = response.getString("price");
                    facebookURL = response.getString("facebook");
                    youtubeURL = response.getString("youtube");
                    gplusURL = response.getString("googleplus");

                    nameText.setText(display_name);
                    priceText.setText(price);
                    fbURLText.setText(facebookURL);
                    youtubeURLText.setText(youtubeURL);
                    googlePlusURLText.setText(gplusURL);

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