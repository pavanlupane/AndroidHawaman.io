package com.demo.pavanlupane.hawamanio;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by PavanLupane on 12/9/15.
 */
public class next24TabActivity extends Activity {

    String result;
    String cityName;
    String stateName;
    String temperatureUnit;
    private static Context context;
    String timezone;

    TableLayout myTable;
    TableRow plus;
    JSONArray dataarray;

    public static Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next24hourslayout);


            result = getIntent().getStringExtra("result");
            cityName = getIntent().getStringExtra("city");
            stateName = getIntent().getStringExtra("state");
            temperatureUnit = getIntent().getStringExtra("degree");
            myTable = (TableLayout) findViewById(R.id.table1Id);


        try {
            JSONObject reader = new JSONObject(result);
            JSONObject current = reader.getJSONObject("currently");
            JSONObject hourly = reader.getJSONObject("hourly");
            dataarray = hourly.getJSONArray("data");

            TextView tempTextView = (TextView) findViewById(R.id.tempTitleTextView);
            tempTextView.setText("Temp"+get_tempunit(temperatureUnit));


            timezone = reader.getString("timezone");
            for(int i = 1 ; i <= 24 ; i++ ) {
                TableRow rowgroup = new TableRow(this);
                JSONObject data = dataarray.getJSONObject(i);

                for (int j = 0; j < 3; j++) {
                    if (j == 0) {
                        long gettime = data.getLong("time");
                        String time = getTime(gettime, timezone);
                        TextView cell = new TextView(this);

                        cell.setWidth(150);
                        cell.setHeight(100);
                        cell.setText(time);
                        cell.setTextColor((Color.parseColor("#000000")));
                        cell.setVisibility(View.VISIBLE);
                        rowgroup.addView(cell);
                        cell.requestLayout();
                        cell.getLayoutParams().height = 100;
                        cell.getLayoutParams().width = 100;
                        cell.setGravity(Gravity.CENTER);
                    } else if (j == 1) {
                        ImageView cell = new ImageView(this);
                        String icon = data.getString("icon");
                        String finalIcon = getImageString(icon);
                        int x = getFlagResourceLink(next24TabActivity.this, finalIcon);
                        cell.setImageResource(x);
                        cell.setMaxWidth(30);
                        cell.setMaxHeight(30);
                        cell.setVisibility(View.VISIBLE);
                        rowgroup.addView(cell);
                        cell.requestLayout();
                        cell.getLayoutParams().height = 150;
                        cell.getLayoutParams().width = 150;

                    } else {
                        TextView cell = new TextView(this);
                        Integer temperature = data.getInt("temperature");
                        cell.setWidth(150);
                        cell.setHeight(100);
                        cell.setText(temperature + get_tempunit(temperatureUnit));
                        cell.setTextColor((Color.parseColor("#000000")));
                        cell.setVisibility(View.VISIBLE);
                        rowgroup.addView(cell);
                        cell.requestLayout();
                        cell.getLayoutParams().height = 100;
                        cell.getLayoutParams().width = 100;
                        cell.setGravity(Gravity.CENTER);
                    }

                }//end of coulumn for
                if (i % 2 != 0) {
                    rowgroup.setBackgroundColor(getResources().getColor(R.color.tableContentGrey));
                }
                myTable.addView(rowgroup);
            }//end of first for loop

            plus = new TableRow(this);

            TextView txtvw1 = new TextView(this);
            txtvw1.setWidth(100);
            txtvw1.setHeight(100);
            txtvw1.setText("");
            plus.addView(txtvw1);

            final Button plusbutton = new Button(this);
            plusbutton.setBackgroundColor(Color.parseColor("#357CB4"));
            plusbutton.setTextSize(40);
            plusbutton.setText("+");
            plusbutton.setTextColor(Color.parseColor("#FFFFFF"));
            plusbutton.setWidth(150);
            plusbutton.setHeight(150);
            plus.addView(plusbutton);

            TextView txtvw2 = new TextView(this);
            txtvw2.setHeight(100);
            txtvw2.setWidth(100);
            txtvw2.setText("");
            plus.addView(txtvw2);

            myTable.addView(plus);

            setContext(this);

            plusbutton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    myTable.removeView(plus);
                    try{
                        for(int i = 25 ; i <= 48 ; i++ ) {
                            TableRow rowgroup = new TableRow(getContext());
                            JSONObject data = dataarray.getJSONObject(i);

                            for (int j = 0; j < 3; j++) {
                                if (j == 0) {
                                    long gettime = data.getLong("time");
                                    String time = getTime(gettime, timezone);
                                    TextView cell = new TextView(getContext());

                                    cell.setWidth(150);
                                    cell.setHeight(100);
                                    cell.setText(time);
                                    cell.setTextColor((Color.parseColor("#000000")));
                                    cell.setVisibility(View.VISIBLE);
                                    rowgroup.addView(cell);
                                    cell.requestLayout();
                                    cell.getLayoutParams().height = 100;
                                    cell.getLayoutParams().width = 100;
                                    cell.setGravity(Gravity.CENTER);
                                } else if (j == 1) {
                                    ImageView cell = new ImageView(getContext());
                                    String icon = data.getString("icon");
                                    String finalIcon = getImageString(icon);
                                    int x = getFlagResourceLink(next24TabActivity.this, finalIcon);
                                    cell.setImageResource(x);
                                    cell.setMaxWidth(30);
                                    cell.setMaxHeight(30);
                                    cell.setVisibility(View.VISIBLE);
                                    rowgroup.addView(cell);
                                    cell.requestLayout();
                                    cell.getLayoutParams().height = 150;
                                    cell.getLayoutParams().width = 150;

                                } else {
                                    TextView cell = new TextView(getContext());
                                    Integer temperature = data.getInt("temperature");
                                    cell.setWidth(150);
                                    cell.setHeight(100);
                                    cell.setText(temperature + get_tempunit(temperatureUnit));
                                    cell.setTextColor((Color.parseColor("#000000")));
                                    cell.setVisibility(View.VISIBLE);
                                    rowgroup.addView(cell);
                                    cell.requestLayout();
                                    cell.getLayoutParams().height = 100;
                                    cell.getLayoutParams().width = 100;
                                    cell.setGravity(Gravity.CENTER);
                                }

                            }//end of coulumn for
                            if (i % 2 != 0) {
                                rowgroup.setBackgroundColor(getResources().getColor(R.color.tableContentGrey));
                            }
                            myTable.addView(rowgroup);
                        }//end of first for loop
                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                }
            });








        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public String get_tempunit(String temperature_unit) {
        if(temperature_unit.equalsIgnoreCase("si"))
            return ""+(char) 0x00B0+"C";
        else
            return ""+(char) 0x00B0+"F";
    }

    public int getFlagResourceLink(Context context, String name) {
        int resId = context.getResources().getIdentifier(name, "drawable", "com.demo.pavanlupane.hawamanio");
        return resId;
    }

    public  String getImageString(String icon){
        if (icon.equalsIgnoreCase("clear-day"))
            return "clear";
        else if (icon.equalsIgnoreCase("clear-night"))
            return "clear_night";
        else if (icon.equalsIgnoreCase("rain"))
            return "rain";
        else if (icon.equalsIgnoreCase("snow"))
            return "snow";
        else if (icon.equalsIgnoreCase("sleet"))
            return "sleet";
        else if (icon.equalsIgnoreCase("wind"))
            return "wind";
        else if (icon.equalsIgnoreCase("fog"))
            return "fog";
        else if (icon.equalsIgnoreCase("cloudy"))
            return "cloudy";
        else if (icon.equalsIgnoreCase("partly-cloudy-day"))
            return "cloud_day";
        else if (icon.equalsIgnoreCase("partly-cloudy-night"))
            return "cloud_night";
        else return null;
    }

    public  String getTime(long timeStp,String timeZone){
        long unixSeconds = timeStp;
        Date date = new Date(unixSeconds*1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("KK:mm a"); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone)); // give a timezone reference for formating (see comment at the bottom
        String formattedDate = sdf.format(date);
        //System.out.println(formattedDate);
        return  formattedDate;
    }
}
