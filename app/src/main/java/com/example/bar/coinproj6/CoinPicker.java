package com.example.bar.coinproj6;

/**
 * Created by Bar Peretz on 4/12/2018.
 */
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Genre extends Activity {
    ListView listView;
    private static final String DEBUG_TAG = "HttpExample";

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coinpickactivity);

        listView = (ListView) findViewById(R.id.list_view);

        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        String result=null;
        try {
            result = downloadUrl();
        }catch(IOException e)
        {}

        ArrayList<String> lstData = new ArrayList<>();
        JSONArray jarray = (JSONArray)result; //ERROR HERE
        try {
            if (jarray != null) {
                for (int i = 0; i < jarray.length(); i++) {
                    lstData.add(jarray.get(i).toString());
                }
            }
        }catch(JSONException j)
        {}

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, lstData);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int itemPosition = position;

                String itemValue = (String) listView.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(), "Position :" + itemPosition + " ListItem :" + itemValue, Toast.LENGTH_LONG).show();
            }
        });
    }

    private String downloadUrl() throws IOException{
        InputStream is = null;

        int len = 500;

        try{
            URL url = new URL("http://coincap.io/coins");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(DEBUG_TAG,"The response is: " + response);
            is = conn.getInputStream();
            String contentAsString = readIt(is, len);
            return contentAsString;
        }finally{
            if(is != null){
                is.close();
            }
        }
    }

    public String readIt(InputStream stream, int len) throws IOException{
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}