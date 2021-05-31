package com.example.coviddailyupdates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import java.security.spec.ECField;

public class WorldCorona extends AppCompatActivity {

    TextView tw_positive, tw_negative, tw_pending, tw_hospitalized, tw_death, tw_total_cases, tw_world_update_date, tw_world_pending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_corona);

        // Initializing ID s
        tw_positive=findViewById(R.id.tw_getWorldPositive);
        tw_death=findViewById(R.id.tw_getWorldDeath);
        tw_negative=findViewById(R.id.tw_getWorldNegative);
        tw_hospitalized=findViewById(R.id.tw_getWorldHospitalized);
        tw_total_cases=findViewById(R.id.tw_getWorldTotalCases);
        tw_world_update_date=findViewById(R.id.tw_getWorldUpdateDate);
        tw_world_pending=findViewById(R.id.tw_getWorldPending);

        World_covid_data world_covid_data=new World_covid_data(WorldCorona.this);
        world_covid_data.getWorldCoronaUpdates(new World_covid_data.World_corona_response() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try{
                    tw_positive.setText(jsonObject.getString("positive"));
                    tw_death.setText(jsonObject.getString("death"));
                    tw_negative.setText(jsonObject.getString("negative"));
                    tw_hospitalized.setText(jsonObject.getString("hospitalizedCurrently"));
                    tw_total_cases.setText(jsonObject.getString("totalTestResults"));
                    tw_world_update_date.setText(jsonObject.getString("lastModified"));
                    tw_world_pending.setText(jsonObject.getString("pending"));
                }catch (Exception e){
                    e.printStackTrace();
                }

                Log.d("myKey", jsonObject.toString());
            }

            @Override
            public void onError(String error) {
                Log.d("myKey", error);
            }
        });
    }
}