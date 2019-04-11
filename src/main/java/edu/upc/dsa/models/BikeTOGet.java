package edu.upc.dsa.models;

public class BikeTOGet {
    private String stationId;
    private String userId;

    public BikeTOGet() {
    }

    public BikeTOGet(String stationId, String userId) {
        this.stationId = stationId;
        this.userId = userId;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
