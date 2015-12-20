package com.demo.pavanlupane.hawamanio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by PavanLupane on 12/6/15.
 */
public class ResultActivity extends AppCompatActivity {

    String result;
    String cityName;
    String stateName;
    String temperatureUnit;
    private CallbackManager callbackManager; //added for fb
    ImageButton myLoginButton;
    Button showMapButton;
    private Toast fbToast;
    private ShareDialog shareDialog;
    Context ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);


        //FB SDK needs to be initialized before using any of its methods
        FacebookSdk.sdkInitialize(getApplicationContext());

        //FB :: initializing the instance of CallbackManager using the CallbackManager.Factory.create method.
        callbackManager = CallbackManager.Factory.create();
        //FB :: Create a shareDialog instance
        shareDialog = new ShareDialog(this);



        ac = this;
        myLoginButton = (ImageButton) findViewById(R.id.fbImageButton);
        myLoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    result = getIntent().getStringExtra("result");
                    temperatureUnit = getIntent().getStringExtra("degree");


                    try {
                        JSONObject weatherObject = new JSONObject(result);
                        JSONObject currentObject = weatherObject.getJSONObject("currently");
                        String icon = currentObject.getString("icon");
                        Integer temperature = currentObject.getInt("temperature");


                    String fbTitleString = "Current Weather in "+cityName+", "+stateName;
                    String fbImageNameString = getImageString(icon);
                    String fbImageString = "http://cs-server.usc.edu:45678/hw/hw8/images/"+fbImageNameString+".png";
                    String fbDescription = icon+", "+temperature+" "+get_tempunit(temperatureUnit);



                    shareDialog = new ShareDialog((Activity) ac);
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle(fbTitleString)
                            .setContentDescription(fbDescription)
                            .setImageUrl(Uri.parse(fbImageString))
                            .setContentUrl(Uri.parse("http://forecast.io"))
                            .build();

                    shareDialog.show(linkContent);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                        @Override
                        public void onSuccess(Sharer.Result result) {
                            fbToast = Toast.makeText(getApplicationContext(), "Facebook Post Successful!", Toast.LENGTH_LONG);
                            fbToast.show();
                        }

                        @Override
                        public void onCancel() {
                            fbToast = Toast.makeText(getApplicationContext(), "Facebook Post Canceled!", Toast.LENGTH_LONG);
                            fbToast.show();
                        }

                        @Override
                        public void onError(FacebookException e) {
                            fbToast = Toast.makeText(getApplicationContext(), "Facebook Post Failed!", Toast.LENGTH_LONG);
                            fbToast.show();
                        }
                    });
                }
            }
        });

        showMapButton = (Button) findViewById(R.id.viewMapButton);
        showMapButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent mapActivity = new Intent(ResultActivity.this, MapActivity.class);
                mapActivity.putExtra("result", result);

                startActivity(mapActivity);
            }

        });

        // FB login button widget
        //myLoginButton = (ImageButton)findViewById(R.id.fbImageButton);
        //loginButton = (LoginButton)findViewById(R.id.login_button);

        //FB login callback or onclick event
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//
//                fbToast = Toast.makeText(getApplicationContext(), "Facebook Login Successful!", Toast.LENGTH_LONG);
//                fbToast.show();
//            }
//
//            @Override
//            public void onCancel() {
//                fbToast = Toast.makeText(getApplicationContext(), "Facebook Login attempt Canceled!", Toast.LENGTH_LONG);
//                fbToast.show();
//
//            }
//
//            @Override
//            public void onError(FacebookException e) {
//                fbToast = Toast.makeText(getApplicationContext(), "Login attempt Failed!", Toast.LENGTH_LONG);
//                fbToast.show();
//            }
//        });





        //TextView t = (TextView) findViewById(R.id.tv);
        ImageView summaryImg = (ImageView) findViewById(R.id.imageView3);
        TextView summaryTitle = (TextView) findViewById(R.id.summaryTitleTextView);
        TextView temperatureTitle = (TextView) findViewById(R.id.temperatureTitleTextView);
        TextView tempUnitText = (TextView) findViewById(R.id.temperatureUnitTextView);
        TextView lowHighTemp = (TextView) findViewById(R.id.lowHighTempTextView);
        TextView precipitationText = (TextView) findViewById(R.id.precipitationValueTextView);
        TextView chanceOfRainText = (TextView) findViewById(R.id.chanceOfRainValueTextView);
        TextView windSpeedText = (TextView) findViewById(R.id.windSpeedValueTextView);
        TextView dewPointText = (TextView) findViewById(R.id.dewPointValueTextView);
        TextView humidityText = (TextView)findViewById(R.id.humidityValueTextView);
        TextView visiilityText = (TextView) findViewById(R.id.visibilityValueTextView);
        TextView sunriseText = (TextView) findViewById(R.id.sunriseValueTextView);
        TextView sunsetText = (TextView) findViewById(R.id.sunsetValueTextView);



        try{
            result = getIntent().getStringExtra("result");
            cityName = getIntent().getStringExtra("city");
            stateName = getIntent().getStringExtra("state");
            temperatureUnit = getIntent().getStringExtra("degree");

            JSONObject weatherObject = new JSONObject(result);
            JSONObject currentObject = weatherObject.getJSONObject("currently");
            JSONObject dailyObject = weatherObject.getJSONObject("daily");
            JSONArray dailyArray = dailyObject.getJSONArray("data");
            JSONObject firstObj = dailyArray.getJSONObject(0);
            String timeZone = weatherObject.getString("timezone");

            String icon = currentObject.getString("icon");
            String summary = currentObject.getString("summary");
            Integer temperature = currentObject.getInt("temperature");

            Double precipitation = currentObject.getDouble("precipIntensity");
            Double rain = 100 * currentObject.getDouble("precipProbability");
            Double windspeed = currentObject.getDouble("windSpeed");
            Double dewpoint = currentObject.getDouble("dewPoint");
            Double humidity = currentObject.getDouble("humidity");
            Integer visibility = currentObject.getInt("visibility");

            summaryTitle.setText(summary + " in " + cityName + ", " + stateName);
            String temperatureUnitStr = get_tempunit(temperatureUnit);
            temperatureTitle.setText(temperature + "");
            tempUnitText.setText(temperatureUnitStr);

            String imageString = getImageString(icon);
            Log.d("Image string Selected", imageString);
            int handle = getFlagResourceLink(ResultActivity.this, imageString);
            summaryImg.setImageResource(handle);

            String minMaxString = getMinMaxTempString(firstObj);

            Log.d("Min Max Temp is ########", minMaxString);
            lowHighTemp.setText(minMaxString);

            String precipitationInten = getPrecipInt(precipitation,temperatureUnit);
            precipitationText.setText(precipitationInten);

            chanceOfRainText.setText((rain)+" %");

            windSpeedText.setText(windspeed+" "+getWindSpeedUnit(temperatureUnit));
            dewPointText.setText(dewpoint+" "+get_tempunit(temperatureUnit));
            humidityText.setText((humidity)*100+" %");

            visiilityText.setText(visibility+" "+getVisibilityUnit(temperatureUnit));
            long sunriseTime = firstObj.getLong("sunriseTime");
            long sunsetTime = firstObj.getLong("sunsetTime");
            sunriseText.setText(getTime(sunriseTime,timeZone));
            sunsetText.setText(getTime(sunsetTime,timeZone));
            //String time = getTime(sunriseTime,timeZone);
            Log.d("Timezone is ###",timeZone);
           // Log.d("Time is ########",time);

           // t.setText(result);
        }catch(Exception e){e.printStackTrace();}


    }

    //FB :: Tapping the login button starts off a new Activity, which returns a result.
    // To receive and handle the result, override the onActivityResult method of your Activity
    // and pass its parameters to the onActivityResult method of CallbackManager.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    public void launchDetailActivity(View view){
        Intent newIntentDetailAct = new Intent(ResultActivity.this, detailsActivity.class);

        newIntentDetailAct.putExtra("result", result);
        newIntentDetailAct.putExtra("city", cityName);
        newIntentDetailAct.putExtra("state", stateName);
        newIntentDetailAct.putExtra("degree", temperatureUnit);
//                    Toast myToast = Toast.makeText(getApplicationContext(), "Inside the post execute", Toast.LENGTH_LONG);
//                    myToast.show();

        startActivity(newIntentDetailAct);

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

    public  String getVisibilityUnit(String tempUnit){
        if(tempUnit.equalsIgnoreCase("us")){
            return "mi";
        }else
            return "km";
    }
    public String getWindSpeedUnit(String tempUnit){

        if(tempUnit.equalsIgnoreCase("us")){
            return "mph";
        }else
        return "m/s";
    }

    public String getPrecipInt(Double precipitation,String temperatureUnit){

        if (temperatureUnit.equalsIgnoreCase("us")) {
            if (precipitation >= 0 && precipitation < 0.002)
                return "None";
            else if (precipitation >= 0.002 && precipitation < 0.017)
                return "Very Light";
            else if (precipitation >= 0.017 && precipitation < 0.1)
                return "Light";
            else if (precipitation >= 0.1 && precipitation < 0.4)
                return "Moderate";
            else if (precipitation >= 0.4)
                return "Heavy";
            else return null;
        } else {
            if (precipitation >= 0 && precipitation < 0.0508)
                return "None";
            else if (precipitation >= 0.0508 && precipitation < 0.4318)
                return "Very Light";
            else if (precipitation >= 0.4318 && precipitation < 2.54)
                return "Light";
            else if (precipitation >= 2.54 && precipitation < 10.16)
                return "Moderate";
            else if (precipitation >= 10.16)
                return "Heavy";
            else return null;
        }
    }

    public String getMinMaxTempString(JSONObject firstObj){
        String minMaxString = "";
        try {
            int tempMin = firstObj.getInt("temperatureMin");
            int tempMax = firstObj.getInt("temperatureMax");

            minMaxString = "L:"+tempMin+(char) 0x00B0+" | "+"H:"+tempMax+(char) 0x00B0;

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

    public int getFlagResourceLink(Context context, String name) {
        int resId = context.getResources().getIdentifier(name, "drawable", "com.demo.pavanlupane.hawamanio");
        return resId;
    }

}
