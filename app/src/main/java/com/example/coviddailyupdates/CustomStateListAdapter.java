package com.example.coviddailyupdates;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomStateListAdapter extends ArrayAdapter {

    public CustomStateListAdapter(@NonNull Context context, int resource, @NonNull List<StateWiseCoronaList> stateWiseCoronaLists) {
        super(context, resource, stateWiseCoronaLists);
        this.stateWiseCoronaLists=stateWiseCoronaLists;
        this.context=context;
    }
    private List<StateWiseCoronaList> stateWiseCoronaLists;
    Context context;

    @Nullable
    @Override
    public String getItem(int position) {
        return stateWiseCoronaLists.get(position).getState();
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.state_list_layout, parent, false);
        TextView tw_getStateNames=convertView.findViewById(R.id.tw_getStateNames);
        tw_getStateNames.setText(getItem(position));
        tw_getStateNames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Corona_State.class);
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
