package com.tieutech.trucksharingapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tieutech.trucksharingapp.adapter.OrderRecyclerViewAdapter;
import com.tieutech.trucksharingapp.data.OrderDatabaseHelper;
import com.tieutech.trucksharingapp.model.Order;
import com.tieutech.trucksharingapp.util.Util;

import java.util.ArrayList;
import java.util.List;

//ABOUT: Activity to display ONLY the Orders that the user created
public class MyOrdersActivity extends AppCompatActivity implements OrderRecyclerViewAdapter.OnOrderListener{

    //View variables
    RecyclerView myOrderRecyclerView;
    OrderRecyclerViewAdapter myOrderRecyclerViewAdapter;

    //Data variables
    int orderID;
    byte[] senderImageBytesArray;
    String senderUsername;
    String receiverUsername;
    String pickupDate;
    String pickupTime;
    String pickupLocation;
    String goodType;
    String orderWeight;
    String orderWidth;
    String orderLength;
    String orderHeight;
    String orderVehicleType;
    String goodDescription;

    //List variables
    List<Order> myOrdersArrayList = new ArrayList<>();
    List<Order> dbOrderList = new ArrayList<>();

    //Database variable
    OrderDatabaseHelper orderDatabaseHelper = new OrderDatabaseHelper(this);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        //Obtain Views
        myOrderRecyclerView = (RecyclerView) findViewById(R.id.myOrderRecyclerView); //RecyclerView to contain Orders

        //Obtain data from the OrderDataHelper database (database of my orders) and add them to myOrdersList
        try {
            Cursor cursor = orderDatabaseHelper.fetchOrderList(); //Obtain the cursor for the database

            //Cursor to the FIRST entry in the Order database
            if (cursor != null) {
                cursor.moveToFirst();

                //Obtain data from the first row of the database
                orderID = cursor.getInt(0);
                senderImageBytesArray = cursor.getBlob(1);
                senderUsername = cursor.getString(2);
                receiverUsername = cursor.getString(3);
                pickupDate = cursor.getString(4);
                pickupTime = cursor.getString(5);
                pickupLocation = cursor.getString(6);
                goodType = cursor.getString(7);
                orderWeight = cursor.getString(8);
                orderWidth = cursor.getString(9);
                orderLength = cursor.getString(10);
                orderHeight = cursor.getString(11);
                orderVehicleType = cursor.getString(12);
                goodDescription = cursor.getString(13);

                //Add all the obtained data from the FIRST order in the database to the myOrdersList
                dbOrderList.add(new Order(senderImageBytesArray,
                        senderUsername,
                        receiverUsername,
                        goodDescription,
                        pickupDate,
                        pickupTime,
                        pickupLocation,
                        goodType,
                        orderWeight,
                        orderWidth,
                        orderLength,
                        orderHeight,
                        orderVehicleType));
            }

            //Obtain the rest of the Orders in the DB
            while (cursor.moveToNext()) {
                orderID = cursor.getInt(0);
                senderImageBytesArray = cursor.getBlob(1);
                senderUsername = cursor.getString(2);
                receiverUsername = cursor.getString(3);

                //Obtain data from the first row of the database
                pickupDate = cursor.getString(4);
                pickupTime = cursor.getString(5);
                pickupLocation = cursor.getString(6);
                goodType = cursor.getString(7);
                orderWeight = cursor.getString(8);
                orderWidth = cursor.getString(9);
                orderLength = cursor.getString(10);
                orderHeight = cursor.getString(11);
                orderVehicleType = cursor.getString(12);
                goodDescription = cursor.getString(13);

                //Add all the obtained data from the FIRST order in the database to the myOrdersList
                dbOrderList.add(new Order(senderImageBytesArray,
                        senderUsername,
                        receiverUsername,
                        goodDescription,
                        pickupDate,
                        pickupTime,
                        pickupLocation,
                        goodType,
                        orderWeight,
                        orderWidth,
                        orderLength,
                        orderHeight,
                        orderVehicleType));
            }
        }
        catch (Exception e) {
            Log.i("Error", "An error has occurred");
        }

        //If myOrdersList is not empty - i.e. there exists some Orders added by the user
        if (!dbOrderList.isEmpty()) {
            myOrdersArrayList.addAll(dbOrderList); //Add the myOrdersList to the allOrdersList
        }
        //RecyclerViewAdapter to link the RecyclerView for Orders to the data
        myOrderRecyclerViewAdapter = new OrderRecyclerViewAdapter(myOrdersArrayList, this, this, this); //Instantiate the Recyclerview Adapter
        myOrderRecyclerView.setAdapter(myOrderRecyclerViewAdapter); //Set the Adapter to the RecyclerView

        //LinearLayoutManager to set the layout of the RecyclerView (and make it horizontal)
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        myOrderRecyclerView.setLayoutManager(layoutManager); //Link the LayoutManager to the RecyclerView
    }

    //Create the options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    //Define actions for selected options menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent mainActivityIntent = new Intent(MyOrdersActivity.this, HomeActivity.class);
                startActivity(mainActivityIntent);
                return true;
            case R.id.action_account:
                Intent accountActivityIntent = new Intent(MyOrdersActivity.this, AccountActivity.class);
                startActivity(accountActivityIntent);
                return true;
            case R.id.action_orders:
                Intent myOrdersActivity = new Intent(MyOrdersActivity.this, MyOrdersActivity.class);
                startActivity(myOrdersActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Listener for the selection of an Order item
    @Override
    public void onOrderClick(int position) {

        //Begin intent to open OrderDetailsActivity
        Intent orderDetailsIntent = new Intent(MyOrdersActivity.this, OrderDetailsActivity.class);

        //Send data to the OrderDetailsActivity
        orderDetailsIntent.putExtra(Util.DATA_SENDER_IMAGE, myOrdersArrayList.get(position).getSenderImage());
        orderDetailsIntent.putExtra(Util.DATA_SENDER_NAME, myOrdersArrayList.get(position).getSenderUsername());
        orderDetailsIntent.putExtra(Util.DATA_RECEIVER_NAME, myOrdersArrayList.get(position).getReceiverUsername());
        orderDetailsIntent.putExtra(Util.DATA_PICKUP_DATE, myOrdersArrayList.get(position).getOrderPickupDate());
        orderDetailsIntent.putExtra(Util.DATA_PICKUP_TIME, myOrdersArrayList.get(position).getOrderPickupTime());
        orderDetailsIntent.putExtra(Util.DATA_PICKUP_LOCATION, myOrdersArrayList.get(position).getOrderPickupLocation());
        orderDetailsIntent.putExtra(Util.DATA_GOOD_TYPE, myOrdersArrayList.get(position).getGoodType());
        orderDetailsIntent.putExtra(Util.DATA_WEIGHT, myOrdersArrayList.get(position).getOrderWeight());
        orderDetailsIntent.putExtra(Util.DATA_WIDTH, myOrdersArrayList.get(position).getOrderWidth());
        orderDetailsIntent.putExtra(Util.DATA_LENGTH, myOrdersArrayList.get(position).getOrderLength());
        orderDetailsIntent.putExtra(Util.DATA_HEIGHT, myOrdersArrayList.get(position).getOrderHeight());
        orderDetailsIntent.putExtra(Util.DATA_VEHICLE_TYPE, myOrdersArrayList.get(position).getOrderVehicleType());

        //Open OrderDetailsActivity
        startActivity(orderDetailsIntent);
    }

    //Listener for the selection of a Share icon
    @Override
    public void onShareClick(int position) {
        Log.i("shareClicked", "shareCickedd");

        //Start the intent to share details of the associated Order
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, goodDescription);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);

    }


}
