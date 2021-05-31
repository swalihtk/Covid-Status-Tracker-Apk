package com.example.coviddailyupdates;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class India_covid_data {
    India_covid_data(Context context){
        this.context=context;
    }

    // Items ..
    Context context;
    public final String INDIA_COVID_API="https://api.covid19india.org/data.json";

    // CallBacks
    public interface India_country_total{
        public void onResponse(JSONObject jsonObject);
        public void onError(String error);
    }
    public interface State_Total_Callback{
        public void onResponse(List<StateWiseCoronaList> stateWiseCoronaLists);
        public void onError(String error);
    }

    // Public methods
    public void getTotal(India_country_total india_country_total){
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, INDIA_COVID_API, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray cases_time_series=response.getJSONArray("cases_time_series");
                    int last_index=cases_time_series.length()-1;
                    JSONObject coronaUpdates=(JSONObject)cases_time_series.get(last_index);
                    india_country_total.onResponse(coronaUpdates);
                } catch (JSONException e) {
                    e.printStackTrace();
                    india_country_total.onError(""+e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                india_country_total.onError(error.toString());
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }


    public void getStateWiseReports(State_Total_Callback state_total_callback){
        List<StateWiseCoronaList> reports=new ArrayList<>();
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, INDIA_COVID_API, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray statewise=response.getJSONArray("statewise");
                    for(int i=0; i<statewise.length(); i++){
                        JSONObject corona_state_wise=statewise.getJSONObject(i);
                        StateWiseCoronaList day_one=new StateWiseCoronaList();


                        day_one.setActive(corona_state_wise.getString("active"));
                        day_one.setDeaths(corona_state_wise.getString("deaths"));
                        day_one.setDeltadeaths(corona_state_wise.getString("deltadeaths"));
                        day_one.setDeltaconfirmed(corona_state_wise.getString("deltaconfirmed"));
                        day_one.setDeltarecovered(corona_state_wise.getString("deltarecovered"));
                        day_one.setLastupdatedtime(corona_state_wise.getString("lastupdatedtime"));
                        day_one.setMigratedother(corona_state_wise.getString("migratedother"));
                        day_one.setRecovered(corona_state_wise.getString("recovered"));
                        day_one.setState(corona_state_wise.getString("state"));
                        day_one.setStatecode(corona_state_wise.getString("statecode"));
                        day_one.setStatenotes(corona_state_wise.getString("statenotes"));
                        reports.add(day_one);
                    }
                    state_total_callback.onResponse(reports);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                state_total_callback.onError(error.toString());
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
