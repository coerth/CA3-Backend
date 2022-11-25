package dtos;


import java.util.Objects;

public class CarTravelDTO {

    private final Integer distance;

    private final String vehicle;

    public CarTravelDTO(Integer distance, String vehicle) {
        this.distance = distance;
        this.vehicle = vehicle;
    }

    public Integer getDistance() {
        return distance;
    }

    public String getVehicle() {
        return vehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarTravelDTO)) return false;
        CarTravelDTO that = (CarTravelDTO) o;
        return getDistance().equals(that.getDistance()) && getVehicle().equals(that.getVehicle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDistance(), getVehicle());
    }

    @Override
    public String toString() {
        return "CarTravelDTO{" +
                "distance=" + distance +
                ", vehicle='" + vehicle + '\'' +
                '}';
    }
}
