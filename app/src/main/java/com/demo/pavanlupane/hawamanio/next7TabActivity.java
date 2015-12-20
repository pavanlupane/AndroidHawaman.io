package com.demo.pavanlupane.hawamanio;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TableLayout;
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
public class next7TabActivity extends Activity {

    String result;
    String cityName;
    String stateName;
    String temperatureUnit;
    String icon;
    String finalIcon;
    int x;
    long gettime;
    String time;
    String minMaxString;
    JSONObject data;

    TableLayout myTable2;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next7dayslayout);

        result = getIntent().getStringExtra("result");
        cityName = getIntent().getStringExtra("city");
        stateName = getIntent().getStringExtra("state");
        temperatureUnit = getIntent().getStringExtra("degree");
        //myTable2 = (TableLayout) findViewById(R.id.table7DaysId);

        try{
            JSONObject reader = new JSONObject(result);
            JSONObject current = reader.getJSONObject("currently");
            JSONObject daily = reader.getJSONObject("daily");
            JSONArray dataarray = daily.getJSONArray("data");
            String timezone = reader.getString("timezone");

            //********* for loop starts here ******

                data = dataarray.getJSONObject(1);
                ImageView imgVw1 = (ImageView) findViewById(R.id.day1image);
                icon = data.getString("icon");
                finalIcon = getImageString(icon);
                x = getFlagResourceLink(next7TabActivity.this, finalIcon);
                imgVw1.setImageResource(x);


                TextView txtVw1 = (TextView) findViewById(R.id.day1date);
                gettime = data.getLong("time");
                time = getTime(gettime, timezone);
                txtVw1.setText(time);

                TextView txtTemp1 = (TextView) findViewById(R.id.day1minmax);
                minMaxString = getMinMaxTempString(data);
                txtTemp1.setText(minMaxString);


            data = dataarray.getJSONObject(2);
            ImageView imgVw2 = (ImageView) findViewById(R.id.day2image);
            icon = data.getString("icon");
            finalIcon = getImageString(icon);
            x = getFlagResourceLink(next7TabActivity.this, finalIcon);
            imgVw2.setImageResource(x);


            TextView txtVw2 = (TextView) findViewById(R.id.day2date);
            gettime = data.getLong("time");
            time = getTime(gettime, timezone);
            txtVw2.setText(time);

            TextView txtTemp2 = (TextView) findViewById(R.id.day2minmax);
            minMaxString = getMinMaxTempString(data);
            txtTemp2.setText(minMaxString);



            data = dataarray.getJSONObject(3);
            ImageView imgVw3 = (ImageView) findViewById(R.id.day3image);
            icon = data.getString("icon");
            finalIcon = getImageString(icon);
            x = getFlagResourceLink(next7TabActivity.this, finalIcon);
            imgVw3.setImageResource(x);


            TextView txtVw3 = (TextView) findViewById(R.id.day3date);
            gettime = data.getLong("time");
            time = getTime(gettime, timezone);
            txtVw3.setText(time);

            TextView txtTemp3 = (TextView) findViewById(R.id.day3minmax);
            minMaxString = getMinMaxTempString(data);
            txtTemp3.setText(minMaxString);



            data = dataarray.getJSONObject(4);
            ImageView imgVw4 = (ImageView) findViewById(R.id.day4image);
            icon = data.getString("icon");
            finalIcon = getImageString(icon);
            x = getFlagResourceLink(next7TabActivity.this, finalIcon);
            imgVw4.setImageResource(x);


            TextView txtVw4 = (TextView) findViewById(R.id.day4date);
            gettime = data.getLong("time");
            time = getTime(gettime, timezone);
            txtVw4.setText(time);

            TextView txtTemp4 = (TextView) findViewById(R.id.day4minmax);
            minMaxString = getMinMaxTempString(data);
            txtTemp4.setText(minMaxString);



            data = dataarray.getJSONObject(5);
            ImageView imgVw5 = (ImageView) findViewById(R.id.day5image);
            icon = data.getString("icon");
            finalIcon = getImageString(icon);
            x = getFlagResourceLink(next7TabActivity.this, finalIcon);
            imgVw5.setImageResource(x);


            TextView txtVw5 = (TextView) findViewById(R.id.day5date);
            gettime = data.getLong("time");
            time = getTime(gettime, timezone);
            txtVw5.setText(time);

            TextView txtTemp5 = (TextView) findViewById(R.id.day5minmax);
            minMaxString = getMinMaxTempString(data);
            txtTemp5.setText(minMaxString);



            data = dataarray.getJSONObject(6);
            ImageView imgVw6 = (ImageView) findViewById(R.id.day6image);
            icon = data.getString("icon");
            finalIcon = getImageString(icon);
            x = getFlagResourceLink(next7TabActivity.this, finalIcon);
            imgVw6.setImageResource(x);


            TextView txtVw6 = (TextView) findViewById(R.id.day6date);
            gettime = data.getLong("time");
            time = getTime(gettime, timezone);
            txtVw6.setText(time);

            TextView txtTemp6 = (TextView) findViewById(R.id.day6minmax);
            minMaxString = getMinMaxTempString(data);
            txtTemp6.setText(minMaxString);


            data = dataarray.getJSONObject(7);
            ImageView imgVw7 = (ImageView) findViewById(R.id.day7image);
            icon = data.getString("icon");
            finalIcon = getImageString(icon);
            x = getFlagResourceLink(next7TabActivity.this, finalIcon);
            imgVw7.setImageResource(x);


            TextView txtVw7 = (TextView) findViewById(R.id.day7date);
            gettime = data.getLong("time");
            time = getTime(gettime, timezone);
            txtVw7.setText(time);

            TextView txtTemp7 = (TextView) findViewById(R.id.day7minmax);
            minMaxString = getMinMaxTempString(data);
            txtTemp7.setText(minMaxString);







//            TableRow rowgroup = new TableRow(this);
//            JSONObject data = dataarray.getJSONObject(1);
//
//            //************** 1st Date and Day code ********
//            long gettime = data.getLong("time");
//            String time = getTime(gettime, timezone);
//            TextView cell = new TextView(this);
//
//            cell.setWidth(150);
//            cell.setHeight(100);
//            cell.setText(time);
//            cell.setTextColor((Color.parseColor("#000000")));
//            cell.setVisibility(View.VISIBLE);
//            rowgroup.addView(cell);
//            cell.requestLayout();
//            cell.getLayoutParams().height = 50;
//            cell.getLayoutParams().width = 500;
//            cell.setGravity(Gravity.CENTER);
//            cell.setTextSize(15);
//            cell.setTypeface(null, Typeface.BOLD);
//
//            //************* 2nd Image goes here ***********
//
//            ImageView cell1 = new ImageView(this);
//            String icon = data.getString("icon");
//            String finalIcon = getImageString(icon);
//            int x = getFlagResourceLink(next7TabActivity.this, finalIcon);
//            cell1.setImageResource(x);
//            cell1.setMaxWidth(30);
//            cell1.setMaxHeight(30);
//            cell1.setVisibility(View.VISIBLE);
//            rowgroup.addView(cell1);
//            cell1.requestLayout();
//            cell1.getLayoutParams().height = 70;
//            cell1.getLayoutParams().width = 70;
//
//
//            myTable2.addView(rowgroup);
//
//            //**************** 3rd L|H goes here ***********
//            TableRow rowgroup2 = new TableRow(this);
//            TextView cell2 = new TextView(this);
//            String minMaxString = getMinMaxTempString(data);
//            cell2.setWidth(150);
//            cell2.setHeight(100);
//            cell2.setText(minMaxString);
//            cell2.setTextColor((Color.parseColor("#000000")));
//            cell2.setVisibility(View.VISIBLE);
//            rowgroup2.addView(cell2);
//            cell2.requestLayout();
//            cell2.getLayoutParams().height = 100;
//            cell2.getLayoutParams().width = 300;
//            cell2.setGravity(Gravity.CENTER);
//            cell2.setTextSize(20);
//
//
//            myTable2.addView(rowgroup2);

        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    public String getMinMaxTempString(JSONObject firstObj){
        String minMaxString = "";
        try {
            int tempMin = firstObj.getInt("temperatureMin");
            int tempMax = firstObj.getInt("temperatureMax");

            minMaxString = "L: "+tempMin+(char) 0x00B0+" | "+"H: "+tempMax+(char) 0x00B0;

            return  minMaxString;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return minMaxString;

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
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMM d"); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone)); // give a timezone reference for formating (see comment at the bottom
        String formattedDate = sdf.format(date);
        //System.out.println(formattedDate);
        return  formattedDate;
    }
}
