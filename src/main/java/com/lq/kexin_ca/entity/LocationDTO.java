package com.lq.kexin_ca.entity;

import java.sql.Timestamp;

public class LocationDTO {

    int userId;
    Timestamp time;
    double longitude;
    double latitude;

    public LocationDTO(int userId, Timestamp time, double longitude, double latitude) {
        this.userId = userId;
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public LocationDTO() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "LocationDTO{" +
                "userId=" + userId +
                ", time=" + time +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
