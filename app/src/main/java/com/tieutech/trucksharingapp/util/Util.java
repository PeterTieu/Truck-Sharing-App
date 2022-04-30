package com.tieutech.trucksharingapp.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

//ABOUT: Utility class for the project
public class Util{

    //Shared Preferences
    public static final String SHARED_PREF_DATA = "shared_pref_data";
    public static final String SHARED_PREF_ACTIVE_USERNAME = "shared_pref_active_username";

    //User Database
    public static final int USER_DATABASE_VERSION = 1;
    public static final String USER_DATABASE_NAME = "user_db";
    public static final String USER_TABLE_NAME = "users_table";
    public static final String USER_IMAGE = "user_image";
    public static final String USER_ID = "user_id";
    public static final String USER_FULL_NAME = "user_full_name";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String PHONE_NUMBER = "phone_number";

    //Order Database
    public static final int ORDER_DATABASE_VERSION = 1;
    public static final String ORDER_DATABASE_NAME = "order_db";
    public static final String ORDER_TABLE_NAME = "orders_table";

    //Order Database column names
    public static final String ORDER_ID = "order_id";
    public static final String ORDER_SENDER_IMAGE = "sender_image";
    public static final String ORDER_SENDER_USERNAME = "sender_username";
    public static final String ORDER_RECEIVER_USERNAME = "receiver_username";

    public static final String ORDER_PICKUP_DATE = "order_pickup_date";
    public static final String ORDER_PICKUP_TIME = "order_pickup_time";
    public static final String ORDER_PICKUP_LOCATION = "order_pickup_location";
    public static final String ORDER_GOOD_TYPE = "order_good_type";
    public static final String ORDER_WEIGHT = "order_weight";
    public static final String ORDER_WIDTH = "order_width";
    public static final String ORDER_LENGTH = "order_length";
    public static final String ORDER_HEIGHT = "order_height";
    public static final String ORDER_VEHICLE_TYPE = "order_vehicle_type";
    public static final String ORDER_GOOD_DESCRIPTION = "good_description";

    //Order data for putExtra (for data passed between activities)
    public static final String DATA_SENDER_IMAGE = "data_sender_image";
    public static final String DATA_SENDER_NAME = "data_sender_name";
    public static final String DATA_RECEIVER_NAME = "data_receiver_name";
    public static final String DATA_PICKUP_DATE = "data_pickup_date";
    public static final String DATA_PICKUP_TIME = "data_pickup_time";
    public static final String DATA_PICKUP_LOCATION = "data_pickup_location";
    public static final String DATA_GOOD_TYPE = "data_good_type";
    public static final String DATA_WEIGHT = "data_weight";
    public static final String DATA_WIDTH = "data_width";
    public static final String DATA_LENGTH = "data_length";
    public static final String DATA_HEIGHT = "data_weight";
    public static final String DATA_VEHICLE_TYPE = "data_vehicle_type";

    //Spinner data
    public static final int SPINNER_GOOD_TYPE = 1;
    public static final int SPINNER_VEHICLE_TYPE = 2;

    //Convert from byte array to String
    public static String getStringFromByteArray(byte[] bytes) {
        String str;
        if (bytes != null ) {
            str = new String(bytes, StandardCharsets.UTF_8); // for UTF-8 encoding
        }
        else {
            str = "";
        }
        return str;
    }

    //Convert from Bitmap to byte array
    public static byte[] getBytesArrayFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    //Convert from byte array to Bitmap
    public static Bitmap getBitmapFromBytesArray(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    //Convert from Bitmap to Drawable
    public static Bitmap getBitmapFromDrawable(Context context, int drawable) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawable);
        return bitmap;
    }
}
