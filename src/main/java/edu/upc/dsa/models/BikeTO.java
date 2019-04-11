package edu.upc.dsa.models;

public class BikeTO {
    private String bikeId;
    private String description;
    private double kms;
    private String stationId;

    public BikeTO() {
    }

    public BikeTO(String bikeId, String description, double kms, String stationId) {
        this.bikeId = bikeId;
        this.description = description;
        this.kms = kms;
        this.stationId = stationId;
    }

    public String getBikeId() {
        return bikeId;
    }

    public void setBikeId(String bikeId) {
        this.bikeId = bikeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getKms() {
        return kms;
    }

    public void setKms(double kms) {
        this.kms = kms;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }
}
