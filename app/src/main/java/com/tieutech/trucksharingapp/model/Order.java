package com.tieutech.trucksharingapp.model;

//ABOUT: Class for each Order object
public class Order {

    byte[] senderImage; //Sender display picture
    String senderUsername; //Sender username
    String receiverUsername; //Receiver username
    String orderPickupDate; //Pickup date
    String orderPickupTime; //Pickup time
    String orderPickupLocation; //Pickup location
    String goodType; //Good type
    String orderWeight; //Weight
    String orderWidth; //Width
    String orderLength; //Length
    String orderHeight; //Height
    String orderVehicleType; //Vehicle type
    String goodDescription; //Good description


    //Constructor #1
    public Order(byte[] senderImage, String senderUsername, String receiverUsername, String goodDescription) {
        this.senderImage = senderImage;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.goodDescription = goodDescription;
    }

    //Constructor #2
    public Order(byte[] senderImage, String senderUsername, String receiverUsername,
                 String goodDescription, String orderPickupDate, String orderPickupTime, String orderPickupLocation,
                 String goodType, String orderWeight, String orderWidth, String orderLength, String orderHeight, String orderVehicleType) {
        this.senderImage = senderImage;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.goodDescription = goodDescription;
        this.orderPickupDate = orderPickupDate;
        this.orderPickupTime = orderPickupTime;
        this.orderPickupLocation = orderPickupLocation;
        this.goodType = goodType;
        this.orderWeight = orderWeight;
        this.orderWidth = orderWidth;
        this.orderLength = orderLength;
        this.orderHeight = orderHeight;
        this.orderVehicleType = orderVehicleType;

    }

    //Setters
    public byte[] getSenderImage() { return senderImage; }
    public String getSenderUsername() {
        return senderUsername;
    }
    public String getReceiverUsername() {
        return receiverUsername;
    }
    public String getOrderPickupDate() { return orderPickupDate; }
    public String getOrderPickupTime() { return orderPickupTime; }
    public String getOrderPickupLocation() { return orderPickupLocation; }
    public String getGoodType() { return goodType; }
    public String getOrderWeight() { return orderWeight; }
    public String getOrderWidth() { return orderWidth; }
    public String getOrderLength() { return orderLength; }
    public String getOrderHeight() { return orderHeight; }
    public String getOrderVehicleType() { return orderVehicleType; }
    public String getGoodDescription() {
        return goodDescription;
    }

    //Getters
    public void setSenderImage(byte[] senderImage) { this.senderImage = senderImage; }
    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }
    public void setReceiverUsername(String receiverUsername) { this.receiverUsername = receiverUsername; }
    public void setOrderPickupDate(String orderPickupDate) { this.orderPickupDate = orderPickupDate; }
    public void setOrderPickupTime(String orderPickupTime) { this.orderPickupTime = orderPickupTime; }
    public void setOrderPickupLocation(String orderPickupLocation) { this.orderPickupLocation = orderPickupLocation; }
    public void setGoodType(String goodType) { this.goodType = goodType; }
    public void setOrderWeight(String orderWeight) { this.orderWeight = orderWeight; }
    public void setOrderWidth(String orderWidth) { this.orderWidth = orderWidth; }
    public void setOrderLength(String orderLength) { this.orderLength = orderLength; }
    public void setOrderHeight(String orderHeight) { this.orderHeight = orderHeight; }
    public void setOrderVehicleType(String orderVehicleType) { this.orderVehicleType = orderVehicleType; }
    public void setGoodDescription(String goodDescription) { this.goodDescription = goodDescription; }
}