package edu.upc.dsa.services;

import edu.upc.dsa.MyBikeManagerGood;
import edu.upc.dsa.MyBikeManagerGoodImpl;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.jws.soap.SOAPBinding;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Api(value = "/bikes", description = "Endpoint to Bikes Service")
@Path("/bikes")
public class MyBikeGoodService {
    private MyBikeManagerGood mb;

    public MyBikeGoodService() throws StationFullException, StationNotFoundException {
        this.mb = MyBikeManagerGoodImpl.getInstance();
        if (mb.numStations() == 0) {
            this.mb = MyBikeManagerGoodImpl.getInstance();
            this.mb.addUser("user1", "Juan", "Lopex");

            this.mb.addStation("Station1","description:: station1", 10, 3, 3);
            this.mb.addStation("Station2","description:: station2", 10, 3, 3);

            this.mb.addBike("bike101", "descripton", 25.45, "Station1");
            this.mb.addBike("bike102", "descripton", 70.3, "Station1");
            this.mb.addBike("bike103", "descripton", 10.2, "Station1");

            this.mb.addBike("bike201", "descripton", 1325.45, "Station2");
            this.mb.addBike("bike202", "descripton", 74430.3, "Station2");
            this.mb.addBike("bike203", "descripton", 1320.2, "Station2");
        }
    }

    @GET
    @ApiOperation(value = "get bikes by station order by kms", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Bike.class),
            @ApiResponse(code = 404, message = "Station not found")
    })
    @Path("/{id}/kms")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBikesByStationOrderByKms(@PathParam("id") String id) {
        List<Bike> bikes;
        try {
            bikes = mb.bikesByStationOrderByKms(id);
        }
        catch (StationNotFoundException e1){
            return Response.status(404).build();
        }
        GenericEntity<List<Bike>> entity = new GenericEntity<List<Bike>>(bikes) {
        };
        return Response.status(201).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "create a new User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= UserTO.class),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Product not found"),
            @ApiResponse(code = 501, message = "Queue full")
    })
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(UserTO user) {
        this.mb.addUser(user.getIdUser(),user.getName(),user.getSurname());
        return Response.status(201).entity(user).build();
    }

    @POST
    @ApiOperation(value = "create a new Station", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= StationTO.class),
            @ApiResponse(code = 500, message = "Error")
    })
    @Path("/station")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newStation(StationTO station) {
        this.mb.addStation(station.getIdStation(),station.getDescription(),station.getMax(),station.getLat(),station.getLon());
        return Response.status(201).entity(station).build();
    }

    @POST
    @ApiOperation(value = "add a new Bike", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Bike.class),
            @ApiResponse(code = 500, message = "Station full"),
            @ApiResponse(code = 404, message = "Station not found")
    })
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBike(BikeTO bike) {
        try{
            this.mb.addBike(bike.getBikeId(),bike.getDescription(),bike.getKms(),bike.getStationId());
            return Response.status(201).entity(bike).build();
        } catch(StationFullException e1){
            return Response.status(500).build();
        } catch(StationNotFoundException e2){
            return Response.status(404).build();
        }
    }

    @POST
    @ApiOperation(value = "get a Bike", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Bike.class),
            @ApiResponse(code = 500, message = "Station full"),
            @ApiResponse(code = 404, message = "Station not found"),
            @ApiResponse(code = 405, message = "User not found")

    })
    @Path("/take")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getBike(BikeTOGet bike) {
        try{
            Bike b = this.mb.getBike(bike.getStationId(),bike.getUserId());
            return Response.status(201).entity(b).build();
        } catch(UserNotFoundException e1){
            return Response.status(405).build();
        } catch(StationNotFoundException e2){
            return Response.status(404).build();
        }
    }

    @GET
    @ApiOperation(value = "get bikes by User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Bike.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}/bikes/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBikesByUser(@PathParam("id") String id) {
        List<Bike> bikes;
        try {
            bikes = mb.bikesByUser(id);
        }
        catch(UserNotFoundException e1) {
            return Response.status(404).build();
        }
        GenericEntity<List<Bike>> entity = new GenericEntity<List<Bike>>(bikes) {
        };
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "get a Bike by params", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Bike.class),
            @ApiResponse(code = 500, message = "Station full"),
            @ApiResponse(code = 404, message = "Station not found"),
            @ApiResponse(code = 405, message = "User not found")

    })
    @Path("/{stationId}/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBikeClone(@PathParam("stationId") String stationId, @PathParam("userId") String userId) {
        try{
            Bike b = this.mb.getBike(stationId,userId);
            return Response.status(201).entity(b).build();
        } catch(UserNotFoundException e1){
            return Response.status(405).build();
        } catch(StationNotFoundException e2){
            return Response.status(404).build();
        }
    }

}
