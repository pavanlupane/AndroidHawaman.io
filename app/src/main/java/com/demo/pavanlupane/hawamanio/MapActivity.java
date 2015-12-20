package com.demo.pavanlupane.hawamanio;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by PavanLupane on 12/9/15.
 */
public class MapActivity extends ActionBarActivity {
    String result;
    JSONObject weatherObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        result = getIntent().getStringExtra("result");
        try {
            weatherObject = new JSONObject(result);

        String lat = weatherObject.getString("latitude");
        String lng = weatherObject.getString("longitude");


        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MapFragment fragment = new MapFragment();

        Bundle bundle = new Bundle();
        bundle.putString("lat", lat);
        bundle.putString("lng", lng);

        fragment.setArguments(bundle);

        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }}