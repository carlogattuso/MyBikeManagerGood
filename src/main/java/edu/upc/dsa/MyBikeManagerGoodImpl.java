package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.LinkedList;

public class MyBikeManagerGoodImpl implements MyBikeManagerGood{
    private static MyBikeManagerGoodImpl instance = new MyBikeManagerGoodImpl();

    final static Logger logger = Logger.getLogger(MyBikeManagerGoodImpl.class);

    public static MyBikeManagerGoodImpl getInstance() {
        return instance;
    }

    List<Station> stations = new ArrayList<>(S);
    HashMap<String,User> userHashMap = new HashMap<>();

    @Override
    public void addUser(String idUser, String name, String surname) {
        User u = new User(idUser,name,surname);
        this.userHashMap.put(idUser,u);
        logger.info("New User: "+u.toString());
        logger.info("User Count: "+this.numUsers());
    }

    @Override
    public void addStation(String idStation, String description, int max, double lat, double lon) {
        Station s = new Station(idStation, description, max, lat, lon);
        this.stations.add(s);
        logger.info("New Station: "+s.toString());
        logger.info("Station Count: "+this.numStations());
    }

    @Override
    public void addBike(String idBike, String description, double kms, String idStation) throws StationFullException, StationNotFoundException {
        Station s = this.findStation(idStation);
        List<Bike> bikes = s.getBikes();
        if(bikes.size()==s.getMax()){
            logger.warn("Station is full");
            throw new StationFullException();
        }
        Bike b = new Bike(idBike,description,kms);
        s.getBikes().add(b);
        logger.info("New Bike: "+b.toString());
        logger.info("Adding Station: "+s.toString());
        logger.info("Station Bikes: "+bikes.size());
    }

    @Override
    public List<Bike> bikesByStationOrderByKms(String idStation) throws StationNotFoundException {
        Station s = findStation(idStation);
        List<Bike> orderByKms = s.getBikes();
        Collections.sort(orderByKms, new Comparator<Bike>() {
            @Override
            public int compare(Bike one, Bike other) {
                return (int) (one.getKms()-other.getKms());
            }
        });
        return orderByKms;
    }

    @Override
    public Bike getBike(String stationId, String userId) throws UserNotFoundException, StationNotFoundException {
        Station s = findStation(stationId);
        User u = findUser(userId);
        LinkedList<Bike> bikes = s.getBikes();
        Bike b = bikes.getFirst();
        s.getBikes().removeFirst();
        u.getBikes().add(b);
        logger.info("Get bike: "+b.toString());
        logger.info("Actual Station: "+s.toString());
        logger.info("Actual User: "+u.toString());
        return b;
    }

    @Override
    public List<Bike> bikesByUser(String userId) throws UserNotFoundException {
        User u = this.findUser(userId);
        return u.getBikes();
    }

    @Override
    public int numUsers() {
        return this.userHashMap.size();
    }

    @Override
    public int numStations() {
        return this.stations.size();
    }

    @Override
    public int numBikes(String idStation) throws StationNotFoundException {
        Station s = findStation(idStation);
        return s.getBikes().size();
    }

    @Override
    public void clear() {
        this.userHashMap.clear();
        this.stations.clear();
    }

    public Station findStation(String idStation) throws StationNotFoundException{
        for(Station s : this.stations){
            if(s.getIdStation().equals(idStation)){
                return s;
            }
        }
        logger.warn("Station not found");
        throw new StationNotFoundException();
    }

    public User findUser(String idUser) throws UserNotFoundException{
        User u = this.userHashMap.get(idUser);
        if(u!=null){
            return u;
        }
        logger.warn("User not found");
        throw new UserNotFoundException();
    }

}
