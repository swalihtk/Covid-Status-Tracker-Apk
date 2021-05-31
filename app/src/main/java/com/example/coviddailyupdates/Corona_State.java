package com.example.coviddailyupdates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Corona_State extends AppCompatActivity {
    int position;

    TextView tw_getActive, tw_getDeaths, tw_getDeltaConfirmed, tw_getDeltaDeaths, tw_getDeltaRecoverd, tw_getLastUpdatedTime, tw_getMigratedOther, tw_getRecoverd,tw_getState, tw_getStateCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_state);

        // setting by Id;
        tw_getActive=findViewById(R.id.tw_getStateActive);
        tw_getDeaths=findViewById(R.id.tw_getStateDeaths);
        tw_getDeltaConfirmed=findViewById(R.id.tw_getStateDeltConfi);
        tw_getDeltaDeaths=findViewById(R.id.tw_getStateDeltDeath);
        tw_getDeltaRecoverd=findViewById(R.id.tw_getStateDeltaReco);
        tw_getLastUpdatedTime=findViewById(R.id.tw_getStateLastUpdatedTime);
        tw_getMigratedOther=findViewById(R.id.tw_getStateMigratdOther);
        tw_getRecoverd=findViewById(R.id.tw_getStateRecover);
        tw_getState=findViewById(R.id.getStateName);
        tw_getStateCode=findViewById(R.id.tw_getStateCode);


        // Getting intent
        Intent intent=getIntent();
        position=intent.getIntExtra("position", 0);

        // Corona Call back method
        India_covid_data india_covid_data=new India_covid_data(Corona_State.this);
        india_covid_data.getStateWiseReports(new India_covid_data.State_Total_Callback() {
            @Override
            public void onResponse(List<StateWiseCoronaList> stateWiseCoronaLists) {
                /*
                 String active;
                String deaths;
                String deltaconfirmed;
                String deltadeaths;
                String deltarecovered;
                String lastupdatedtime;
                String migratedother;
                String recovered;
                public String state;
                String statecode;
     */
                tw_getActive.setText(stateWiseCoronaLists.get(position).getActive());
                tw_getDeaths.setText(stateWiseCoronaLists.get(position).getDeaths());
                tw_getDeltaConfirmed.setText(stateWiseCoronaLists.get(position).getDeltaconfirmed());
                tw_getDeltaDeaths.setText(stateWiseCoronaLists.get(position).getDeltadeaths());
                tw_getDeltaRecoverd.setText(stateWiseCoronaLists.get(position).getDeltarecovered());
                tw_getLastUpdatedTime.setText(stateWiseCoronaLists.get(position).getLastupdatedtime());
                tw_getMigratedOther.setText(stateWiseCoronaLists.get(position).getMigratedother());
                tw_getRecoverd.setText(stateWiseCoronaLists.get(position).getRecovered());
                tw_getState.setText(stateWiseCoronaLists.get(position).getState());
                tw_getStateCode.setText(stateWiseCoronaLists.get(position).getStatecode());

                //Toast.makeText(Corona_State.this, stateWiseCoronaLists.get(position).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}