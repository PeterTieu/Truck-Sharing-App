package com.tieutech.trucksharingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.tieutech.trucksharingapp.util.Util;

//ABOUT: Displays the details of an order that is clicked on in either HomeActivity or MyOrdersActivity
public class OrderDetailsActivity extends AppCompatActivity {

    //View variables
    ImageView orderDetailsUserImageImageView;
    TextView orderDetailsSenderNameTextView;
    TextView orderDetailsPickupTimeTextView;
    TextView orderDetailsPickupDateTextView;
    TextView orderDetailsPickupLocationEditText;
    TextView orderDetailsReceiverNameTextView;
    TextView orderDetailsGoodTypeTextView;
    TextView orderDetailsVehicleTypeTextView;
    TextView orderDetailsWeightTextView;
    TextView orderDetailsHeightTextView;
    TextView orderDetailsWidthTextView;
    TextView orderDetailsLengthTextView;
    Button callDriverButton;

    //Data variables
    byte[] senderImageByteArray;
    String senderName;
    String pickupTime;
    String pickupDate;
    String pickupLocation;
    String receiverName;
    String goodType;
    String vehicleType;
    String weight;
    String height;
    String width;
    String length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        //Obtain views
        orderDetailsUserImageImageView = (ImageView) findViewById(R.id.orderDetailsUserImageImageView);
        orderDetailsSenderNameTextView = (TextView) findViewById(R.id.orderDetailsSenderNameTextView);
        orderDetailsPickupTimeTextView = (TextView) findViewById(R.id.orderDetailsPickupTimeTextView);
        orderDetailsPickupDateTextView = (TextView) findViewById(R.id.orderDetailsPickupDateTextView);
        orderDetailsPickupLocationEditText = (TextView) findViewById(R.id.orderDetailsPickupLocationEditText);
        orderDetailsReceiverNameTextView = (TextView) findViewById(R.id.orderDetailsReceiverNameTextView);
        orderDetailsGoodTypeTextView = (TextView) findViewById(R.id.orderDetailsGoodTypeTextView);
        orderDetailsVehicleTypeTextView = (TextView) findViewById(R.id.orderDetailsVehicleTypeTextView);
        orderDetailsWeightTextView = (TextView) findViewById(R.id.orderDetailsWeightTextView);
        orderDetailsHeightTextView = (TextView) findViewById(R.id.orderDetailsHeightTextView);
        orderDetailsWidthTextView = (TextView) findViewById(R.id.orderDetailsWidthTextView);
        orderDetailsLengthTextView = (TextView) findViewById(R.id.orderDetailsLengthTextView);
        callDriverButton = (Button) findViewById(R.id.callDriverButton);

        //Obtain data passed from the HomeActivity or MyOrdersActivity for the Order item that is clicked on
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            senderImageByteArray = extras.getByteArray(Util.DATA_SENDER_IMAGE);
            senderName = extras.getString(Util.DATA_SENDER_NAME);
            pickupDate = extras.getString(Util.DATA_PICKUP_DATE);
            pickupTime = extras.getString(Util.DATA_PICKUP_TIME);
            pickupLocation = extras.getString(Util.DATA_PICKUP_LOCATION);
            receiverName = extras.getString(Util.DATA_RECEIVER_NAME);
            goodType = extras.getString(Util.DATA_GOOD_TYPE);
            vehicleType = extras.getString(Util.DATA_VEHICLE_TYPE);
            weight = extras.getString(Util.DATA_WEIGHT);
            height = extras.getString(Util.DATA_HEIGHT);
            width = extras.getString(Util.DATA_WIDTH);
            length = extras.getString(Util.DATA_LENGTH);
        }

        //Display data in the views
        orderDetailsUserImageImageView.setImageBitmap(Util.getBitmapFromBytesArray(senderImageByteArray));
        orderDetailsSenderNameTextView.setText(senderName);
        orderDetailsPickupTimeTextView.setText(pickupTime);
        orderDetailsPickupDateTextView.setText(pickupDate);
        orderDetailsPickupLocationEditText.setText(pickupLocation);
        orderDetailsReceiverNameTextView.setText(receiverName);
        orderDetailsGoodTypeTextView.setText(goodType);
        orderDetailsVehicleTypeTextView.setText(vehicleType);
        orderDetailsWeightTextView.setText(weight);
        orderDetailsHeightTextView.setText(height);
        orderDetailsWidthTextView.setText(width);
        orderDetailsLengthTextView.setText(length);
    }

    //Listener for the "Call Driver" Button
    public void callDriver(View view) {
        Intent myOrdersIntent = new Intent(OrderDetailsActivity.this, MyOrdersActivity.class);
        startActivity(myOrdersIntent);
    }
}
