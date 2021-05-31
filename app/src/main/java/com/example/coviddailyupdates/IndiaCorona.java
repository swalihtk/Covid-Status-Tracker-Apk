package com.example.coviddailyupdates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class IndiaCorona extends AppCompatActivity {

    TextView tw_getDailyConfirmed, tw_getDailyDeath, tw_getDailyRecovered;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_india_corona);

        // Initializing ID s;
        tw_getDailyConfirmed=findViewById(R.id.tw_getDailyConfirmed);
        tw_getDailyDeath=findViewById(R.id.tw_getDailyDeath);
        tw_getDailyRecovered=findViewById(R.id.tw_getDailyRecoverd);
        listView=findViewById(R.id.lw_stateLists);

        India_covid_data india_covid_data=new India_covid_data(IndiaCorona.this);
        india_covid_data.getTotal(new India_covid_data.India_country_total() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                //Toast.makeText(IndiaCorona.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();

             String dailyconfirmed="";
             String dailyrecovered="";
             String dailydeceased="";

             try{
                 dailyconfirmed=jsonObject.getString("dailyconfirmed");
                 dailyrecovered=jsonObject.getString("dailyrecovered");
                 dailydeceased=jsonObject.getString("dailydeceased");
             }catch (Exception e){
                 e.printStackTrace();
             }
             tw_getDailyConfirmed.setText(dailyconfirmed);
             tw_getDailyRecovered.setText(dailyrecovered);
             tw_getDailyDeath.setText(dailydeceased);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(IndiaCorona.this, error, Toast.LENGTH_SHORT).show();
            }
        });

        // State wise corona updates
        india_covid_data.getStateWiseReports(new India_covid_data.State_Total_Callback() {
            @Override
            public void onResponse(List<StateWiseCoronaList> stateWiseCoronaLists) {
                CustomStateListAdapter adapter=new CustomStateListAdapter(IndiaCorona.this, R.layout.state_list_layout, stateWiseCoronaLists);
                //Toast.makeText(IndiaCorona.this, stateWiseCoronaLists.get(1).getStateOnly(), Toast.LENGTH_SHORT).show();

               listView.setAdapter(adapter);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(IndiaCorona.this, error, Toast.LENGTH_SHORT).show();
            }
        });


    }
}