package dtos;


import java.util.Objects;

public class CarTravelDTO {

    private final float distance;

    private final String vehicle;


    public CarTravelDTO(float distance, String vehicle) {
        this.distance = distance;
        this.vehicle = vehicle;
    }


    public float getDistance() {
        return distance;
    }

    public String getVehicle() {
        return vehicle;
    }
}
