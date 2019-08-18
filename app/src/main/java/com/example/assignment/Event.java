package com.example.assignment;

public class Event {
private String id;
private String organiser;
private String eventName;
private int eventPax;
private String address;
private String date;
private String time;
private String eventImageID;
private String eventDescription;
private int paxJoined;
private String paxName;

    public String getPaxName() {
        return paxName;
    }

    public void setPaxName(String paxName) {
        this.paxName = paxName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public int getPaxJoined() {
        return paxJoined;
    }

    public void setPaxJoined(int paxJoined) {
        this.paxJoined = paxJoined;
    }

    public Event(){};

    public Event(String organiser, String eventName, int eventPax, String address, String date, String time,String eventImageID)
    {
        this.organiser=organiser;
        this.eventName = eventName;
        this.eventPax = eventPax;
        this.address = address;
        this.date   =date;
        this.time   =time;
this.eventImageID = eventImageID;

    }

    public String getEventImageID() {
        return eventImageID;
    }

    public void setEventImageID(String eventImageID) {
        this.eventImageID = eventImageID;
    }

    public String getOrganiser(){return organiser;}

    public void setOrganiser(String organiser){
        this.organiser=organiser;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventPax() {
        return eventPax;
    }

    public void setEventPax(int eventPax) {
        this.eventPax = eventPax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

