package com.example.assignment;

public class eventOpt {
    private String organiser;
    private String eventName;
    private int eventPax;

    public eventOpt() {
    }

    public eventOpt(String organiser, String eventName, int eventPax) {
        this.organiser = organiser;
        this.eventName = eventName;
        this.eventPax = eventPax;
    }

    public String getOrganiser() {
        return organiser;
    }

    public void setOrganiser(String organiser) {
        this.organiser = organiser;
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
}
