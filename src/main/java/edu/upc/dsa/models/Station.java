package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class Station {
    private String idStation;
    private String description;
    private int max;
    private double lat;
    private double lon;
    private LinkedList<Bike> bikes;


    public Station() {
    }

    public Station(String idStation, String description, int max, double lat, double lon) {
        this.idStation = idStation;
        this.description = description;
        this.max = max;
        this.lat = lat;
        this.lon = lon;
        this.bikes = new LinkedList<>();
    }

    public String getIdStation() {
        return idStation;
    }

    public void setIdStation(String idStation) {
        this.idStation = idStation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public LinkedList<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(LinkedList<Bike> bikes) {
        this.bikes = bikes;
    }

    @Override
    public String toString() {
        return "Station [id="+idStation+", description=" + description + ", maxBikes=" + max + ", latitude=" + lat + ", longitude=" + lon + ", bikes=" + bikes.size() +"]";
    }
}
