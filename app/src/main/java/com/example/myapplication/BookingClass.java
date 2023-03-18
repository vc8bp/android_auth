package com.example.myapplication;

public class BookingClass {
    private String name;
    private String date;
    private String number;
    private String service;

    public BookingClass(String name, String date, String number, String service) {
        this.name = name;
        this.date = date;
        this.number = number;
        this.service = service;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getNumber() {
        return number;
    }

    public String getService() {
        return service;
    }



}
