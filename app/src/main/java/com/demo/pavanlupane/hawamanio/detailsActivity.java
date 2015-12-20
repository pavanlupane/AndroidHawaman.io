package com.demo.pavanlupane.hawamanio;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TextView;

import org.json.JSONObject;

/**
 * Created by PavanLupane on 12/8/15.
 */
public class detailsActivity extends TabActivity {

    String result;
    String cityName;
    String stateName;
    String temperatureUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        try {

            result = getIntent().getStringExtra("result");
            cityName = getIntent().getStringExtra("city");
            stateName = getIntent().getStringExtra("state");
            temperatureUnit = getIntent().getStringExtra("degree");

            JSONObject weatherObject = new JSONObject(result);
            String timeZone = weatherObject.getString("timezone");

            TextView moreDetailsTitle = (TextView) findViewById(R.id.moreDetailsTitle);

            moreDetailsTitle.setText("More Details for "+cityName+", "+stateName);
            Log.d("Here is new Contetnt ::", "Timezone:" + timeZone + "City" + cityName + "state" + stateName + "tempUnit" + temperatureUnit);

            // create the TabHost that will contain the Tabs
            TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);


            TabHost.TabSpec tab1 = tabHost.newTabSpec("First Tab");
            TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab");
            Intent intent24Hr = new Intent(this,next24TabActivity.class);
            Intent intent7Day = new Intent(this,next7TabActivity.class);

            // Set the Tab name and Activity
            // that will be opened when particular Tab will be selected
            tab1.setIndicator("Next 24 Hours");
            intent24Hr.putExtra("result", result);
            intent24Hr.putExtra("city", cityName);
            intent24Hr.putExtra("state", stateName);
            intent24Hr.putExtra("degree", temperatureUnit);
            tab1.setContent(intent24Hr);


            tab2.setIndicator("Next 7 Days");
            intent7Day.putExtra("result", result);
            intent7Day.putExtra("city", cityName);
            intent7Day.putExtra("state", stateName);
            intent7Day.putExtra("degree", temperatureUnit);
            tab2.setContent(intent7Day);

            /** Add the tabs  to the TabHost to display. */
            tabHost.addTab(tab1);
            tabHost.addTab(tab2);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
