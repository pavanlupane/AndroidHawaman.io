package com.demo.pavanlupane.hawamanio;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;


public class LaunchForm extends AppCompatActivity {

    Spinner mySpinner;
    String stateName;
    TextView streetAddr;
    TextView cityName;
    RadioButton usButton;
    TextView error;
    public String temperature;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_form);
        Button submitButton = (Button) findViewById(R.id.searchButton);
        ImageView img = (ImageView)findViewById(R.id.imageView);
        temperature = "us";
        mySpinner = (Spinner) findViewById(R.id.spinner);
        streetAddr = (TextView)findViewById(R.id.streetText);
        cityName = (TextView)findViewById(R.id.cityText);
        JSONArray arrayHandle = null;



        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                error = (TextView)findViewById(R.id.errorTextView);
                if(!validate()) {
                    error.setText("");
                    //url ="https://api.forecast.io/forecast/f528522997d10cc53f360de1662eaba2/34.0205798,118.2813455?units=us";
                    stateName = mySpinner.getSelectedItem().toString();
                    url = "http://pavanswebapps-env.elasticbeanstalk.com/index/forecastServer.php?streetAddress=" + streetAddr.getText().toString().replaceAll(" ", "+") + "&city=" + cityName.getText().toString().replaceAll(" ", "+") + "&selectState=" + stateName + "&degree=" + temperature;
                    //Log.d("My Link is::",url);
                    //url = "http://pavanswebapps-env.elasticbeanstalk.com/index/forecastServer.php?streetAddress=portland+street&city=los+angeles&selectState=ca&degree=si";
                    new ProcessJSON().execute(url);
                    //Toast myToast= Toast.makeText(getApplicationContext(), "Inside Search function!", Toast.LENGTH_LONG);
                    //myToast.show();
                }
            }
        });
        img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://forecast.io"));
                startActivity(intent);
            }
        });
    }

    public boolean validate() {

         error = (TextView)findViewById(R.id.errorTextView);


        int flag = 1;
        if (streetAddr.getText().toString().trim().length() <= 0) {
            error.setText("Please enter a street address");
            flag = 0;
        } else if (cityName.getText().toString().trim().length() <= 0) {

            error.setText("Please enter a city");
            flag = 0;
        } else if (mySpinner.getSelectedItem().toString().equals("Select")) {

            error.setText("Please select a state");
            flag = 0;
        }
        if(flag==0)
        {
            return true;
        }
        else {
            return false;
        }
    }


    public void launchAbout(View view){

        Intent aboutIntent = new Intent(this,aboutActivity.class);
        startActivity(aboutIntent);
    }

    public  void clearFunction(View view){
        clearMe();
    }

    public  void clearMe(){
        usButton = (RadioButton) findViewById(R.id.FahradioButton);

        streetAddr.setText("");
        cityName.setText("");
        mySpinner.setSelection(0);
        usButton.setChecked(true);
        temperature="us";

//        Toast clearToast= Toast.makeText(getApplicationContext(), "Form Cleared!", Toast.LENGTH_SHORT);
//        clearToast.show();


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launch_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class ProcessJSON extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... urls){
            //JSONParser jParser = new JSONParser();
            String stream = null;
            // Getting JSON from URL
//            JSONObject json = jParser.getJSONFromUrl(urls[0]);
//            return json;
            String urlString = urls[0];

            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlString);
            return stream;
        }

        protected void onPostExecute(String stream) {
            //pDialog.dismiss();
            Log.d("Debug", "Success");
            if (stream != null){
                try {
                    JSONObject weatherObject = new JSONObject(stream);
                    Intent new_Intent = new Intent(LaunchForm.this, ResultActivity.class);

                    new_Intent.putExtra("result", stream);
//                    Log.d("Check this value ::",weatherObject.getString("icon"));
                    new_Intent.putExtra("city", cityName.getText().toString().trim());
                    new_Intent.putExtra("state", mySpinner.getSelectedItem().toString());
                    new_Intent.putExtra("degree", temperature);
//                    Toast myToast = Toast.makeText(getApplicationContext(), "Inside the post execute", Toast.LENGTH_LONG);
//                    myToast.show();

                    startActivity(new_Intent);
                    clearMe();


                } catch (Exception e) {
                    e.printStackTrace();
                }
             }
            }
        }//end of sync class

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.FahradioButton:
                if (checked)
                    temperature = "us";
                break;
            case R.id.celradioButton:
                if (checked)
                    temperature = "si";
                break;
        }
    }
}


