package com.example.coviddailyupdates;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class World_covid_data {

    World_covid_data(Context context){
        this.context=context;
    }

    // Public and private items
    public static final String WORLD_CORONA_API="https://api.covidtracking.com/v1/us/current.json";
    private Context context;

    // Callback
    public interface World_corona_response{
        public void onResponse(JSONObject jsonObject);
        public void onError(String error);
    }

    public void getWorldCoronaUpdates(World_corona_response world_corona_response){
        JsonArrayRequest request= new JsonArrayRequest(Request.Method.GET, WORLD_CORONA_API, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject world_corona_object_json=response.getJSONObject(0);
                    world_corona_response.onResponse(world_corona_object_json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                world_corona_response.onError(error.toString());
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
