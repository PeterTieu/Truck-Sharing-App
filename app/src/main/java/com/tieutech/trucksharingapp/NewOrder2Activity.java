package com.tieutech.trucksharingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.tieutech.trucksharingapp.data.OrderDatabaseHelper;
import com.tieutech.trucksharingapp.data.UserDatabaseHelper;
import com.tieutech.trucksharingapp.model.Order;
import com.tieutech.trucksharingapp.util.Util;

//ABOUT: Activity 2 of 2 to create a new Order
public class NewOrder2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //View variables
    private Spinner goodTypeSpinner;
    private Spinner vehicleTypeSpinner;
    private EditText weightEditText;
    private EditText widthEditText;
    private EditText lengthEditText;
    private EditText heightEditText;

    //String array variables for Spinner values
    private static final String[] goodTypeItems = {"Furniture", "Dry goods", "Food", "Building material", "Other"};
    private static final String[] vehicleTypeItems = {"Truck", "Van", "Refrigerated Truck", "Mini-truck", "Other"};

    //Data variables
    byte[] senderImageBytesArray;
    String senderName;
    String receiverName;
    String pickupDate;
    String pickupTime;
    String pickupLocation;
    String goodTypeSelected;
    String weight;
    String width;
    String length;
    String height;
    String vehicleTypeSelected;
    String goodDescription;

    //Database variables
    OrderDatabaseHelper orderDatabaseHelper = new OrderDatabaseHelper(this);
    UserDatabaseHelper userDatabaseHelper = new UserDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order_2);

        //Obtain views
        goodTypeSpinner = (Spinner) findViewById(R.id.goodTypeSpinner);
        vehicleTypeSpinner = (Spinner) findViewById(R.id.vehicleTypeSpinner);
        weightEditText = (EditText) findViewById(R.id.weightEditText);
        widthEditText = (EditText) findViewById(R.id.widthEditText);
        lengthEditText = (EditText) findViewById(R.id.lengthEditText);
        heightEditText = (EditText) findViewById(R.id.heightEditText);

        //Obtain the sender name (i.e. the user who created the order - also, the current username)
        SharedPreferences prefs = getSharedPreferences(Util.SHARED_PREF_DATA, MODE_PRIVATE); //Created a SharedPreference based on the key specified by SHARED_PREF_DATA
        senderName = prefs.getString(Util.SHARED_PREF_ACTIVE_USERNAME, ""); //Obtain the active username

        //Obtain data passed from NewOrder1Activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            receiverName = extras.getString(Util.DATA_RECEIVER_NAME);
            pickupDate = extras.getString(Util.DATA_PICKUP_DATE);
            pickupTime = extras.getString(Util.DATA_PICKUP_TIME);
            pickupLocation = extras.getString(Util.DATA_PICKUP_LOCATION);
        }

        //Set up spinner for Good Type
        ArrayAdapter<String> adapterGoodType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, goodTypeItems);
        adapterGoodType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        goodTypeSpinner.setAdapter(adapterGoodType);
        goodTypeSpinner.setOnItemSelectedListener(this);

        //Set up spinner for Good Type
        ArrayAdapter<String> adapterVehicleType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, vehicleTypeItems);
        adapterVehicleType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleTypeSpinner.setAdapter(adapterVehicleType);
        vehicleTypeSpinner.setOnItemSelectedListener(this);
    }

    //Listener for Spinners
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        //Listener for item selected in the Good Type Spinner
        if (parent.getId() == R.id.goodTypeSpinner) {
            switch (position) {
                case 0:
                    goodTypeSelected = goodTypeItems[0];
                    break;
                case 1:
                    goodTypeSelected = goodTypeItems[1];
                    break;
                case 2:
                    goodTypeSelected = goodTypeItems[2];
                    break;
                case 3:
                    goodTypeSelected = goodTypeItems[3];
                    break;
                case 4:
                    goodTypeSelected = goodTypeItems[4];
                    break;
            }
        }

        //Listener for item selected in the Vehicle Type Spinner
        if (parent.getId() == R.id.vehicleTypeSpinner) {
            switch (position) {
                case 0:
                    vehicleTypeSelected = vehicleTypeItems[0];
                    break;
                case 1:
                    vehicleTypeSelected = vehicleTypeItems[1];
                    break;
                case 2:
                    vehicleTypeSelected = vehicleTypeItems[2];
                    break;
                case 3:
                    vehicleTypeSelected = vehicleTypeItems[3];
                    break;
                case 4:
                    vehicleTypeSelected = vehicleTypeItems[4];
                    break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

    //Listener for the "Create Order" Button
    public void createOrderClick(View view) {

        //==== CODE BELOW: ========================================================================
        // Obtains all necessary data of the order to create a new entry in the Order database ====

        //--------- OBTAIN ENTERED DATA ----------------
        weight = weightEditText.getText().toString();
        width = widthEditText.getText().toString();
        length = lengthEditText.getText().toString();
        height = heightEditText.getText().toString();

        //--------- OBTAIN USER'S ACCOUNT DISPLAY PICTURE DATA ----------------
        //Cursor to the FIRST entry in the User database
        try {
            Cursor cursor = userDatabaseHelper.fetchUserList();

            //Obtain the FIRST Order in the database
            if (cursor != null) {

                cursor.moveToFirst(); //Move to the FIRST row in the database
                String username = cursor.getString(3); //Obtain the username

                //If the user's name is the same as the active user
                if (username.equals(senderName)) {
                    senderImageBytesArray = cursor.getBlob(1); //Obtain the user's display picture
                }
            }

            //Cursor to the rest of entries in the User database
            while (cursor.moveToNext()) {

                String username = cursor.getString(3); //Obtain the username

                if (username.equals(senderName)) {
                    senderImageBytesArray = cursor.getBlob(1); //Obtain the user's display picture
                }
            }
        }
        catch (Exception e) {
            Log.i("Error", "An error has occurred");
        }

        //--------- OBTAIN GOOD DESCRIPTION ----------------
        goodDescription = goodTypeSelected
                + " to be picked up from " + pickupLocation
                + " on " + pickupDate + " at " + pickupTime
                + " by " + vehicleTypeSelected + " mode of transport.";


        //==== CODE BELOW: ========================================================================
        // Takes all obtained data for the Order and inserts them as a new entry in the Order database

        long rowID;

        //If the User HAS an existing account display picture
        if (senderImageBytesArray != null) {

            //Insert a new entry to the Order database using all the obtained data
            rowID = orderDatabaseHelper.insertOrder(new Order(
                    senderImageBytesArray,
                    senderName,
                    receiverName,
                    goodDescription,
                    pickupDate,
                    pickupTime,
                    pickupLocation,
                    goodTypeSelected,
                    weight,
                    width,
                    length,
                    height,
                    vehicleTypeSelected));
        }
        //If the User DOES NOT have an existing account display picture
        else {
            //Obtain the byte array of the default image drawable so that it could be inserted into the Order database
            Bitmap bitmap = Util.getBitmapFromDrawable(this, R.drawable.order_image_default);
            byte[] byteImage = Util.getBytesArrayFromBitmap(bitmap);

            //Insert a new entry to the Order database using all the obtained data
            rowID = orderDatabaseHelper.insertOrder(new Order(
                    byteImage,
                    senderName,
                    receiverName,
                    goodDescription,
                    pickupDate,
                    pickupTime,
                    pickupLocation,
                    goodTypeSelected,
                    weight,
                    width,
                    length,
                    height,
                    vehicleTypeSelected));
        }

        //If the inserting of a new Order entry into the Order database is successful
        if (rowID > 0) {
            makeToast("New order created");
        }
        //If the inserting of a new Order entry into the Order database is NOT successful
        else {
            makeToast("Error creating order");
        }

        //Start the HomActivity
        Intent homeActivityIntent = new Intent(NewOrder2Activity.this, HomeActivity.class);
        startActivity(homeActivityIntent);
    }

    public void makeToast(String message) {
        Toast.makeText(NewOrder2Activity.this, message, Toast.LENGTH_SHORT).show();
    }
}