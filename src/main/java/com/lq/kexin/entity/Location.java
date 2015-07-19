package com.lq.kexin.entity;

import java.sql.Timestamp;

public class Location {

    int userId;
    Timestamp time;
    double longitude;
    double latitude;

    public Location(int userId, Timestamp time, double longitude, double latitude) {
        this.userId = userId;
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Location() {
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Location{" +
                "userId=" + userId +
                ", time='" + time + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
