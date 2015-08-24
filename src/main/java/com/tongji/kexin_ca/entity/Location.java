package com.tongji.kexin_ca.entity;

import com.tongji.kexin_ca.dto.LocationDTO;

import java.sql.Timestamp;

public class Location {

    int userId;
    Timestamp time;
    double longitude;
    double latitude;
    int result;

    public Location(int userId, Timestamp time, double longitude, double latitude, int result) {
        this.userId = userId;
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
        this.result = result;//0：正常，1：异常
    }

    public Location(LocationDTO locationDTO, int result) {
        this.userId = locationDTO.getUserId();
        this.time = locationDTO.getTime();
        this.longitude = locationDTO.getLongitude();
        this.latitude = locationDTO.getLatitude();
        this.result = result;
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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "userId=" + userId +
                ", time=" + time +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", result=" + result +
                '}';
    }
}
