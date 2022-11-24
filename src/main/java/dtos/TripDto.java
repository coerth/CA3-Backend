package dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class TripDto implements Serializable {
    private final Integer id;
    @NotNull
    private final Float distance;
    @NotNull
    private final Float emission;
    @NotNull
    private final Float cost;
    @NotNull
    private final JourneyDto journey;
    @NotNull
    private final FuelDto fuel;
    @NotNull
    private final TransportationDto transportation;

    public TripDto(Integer id, Float distance, Float emission, Float cost, JourneyDto journey, FuelDto fuel, TransportationDto transportation) {
        this.id = id;
        this.distance = distance;
        this.emission = emission;
        this.cost = cost;
        this.journey = journey;
        this.fuel = fuel;
        this.transportation = transportation;
    }

    public Integer getId() {
        return id;
    }

    public Float getDistance() {
        return distance;
    }

    public Float getEmission() {
        return emission;
    }

    public Float getCost() {
        return cost;
    }

    public JourneyDto getJourney() {
        return journey;
    }

    public FuelDto getFuel() {
        return fuel;
    }

    public TransportationDto getTransportation() {
        return transportation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripDto entity = (TripDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.distance, entity.distance) &&
                Objects.equals(this.emission, entity.emission) &&
                Objects.equals(this.cost, entity.cost) &&
                Objects.equals(this.journey, entity.journey) &&
                Objects.equals(this.fuel, entity.fuel) &&
                Objects.equals(this.transportation, entity.transportation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, distance, emission, cost, journey, fuel, transportation);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "distance = " + distance + ", " +
                "emission = " + emission + ", " +
                "cost = " + cost + ", " +
                "journey = " + journey + ", " +
                "fuel = " + fuel + ", " +
                "transportation = " + transportation + ")";
    }

    public static class JourneyDto implements Serializable {
        private final Integer id;
        @Size(max = 45)
        @NotNull
        private final String name;
        @NotNull
        private final LocalDate date;
        @NotNull
        private final Float totalEmission;
        @NotNull
        private final Float totalDistance;
        @NotNull
        private final Float totalCost;

        public JourneyDto(Integer id, String name, LocalDate date, Float totalEmission, Float totalDistance, Float totalCost) {
            this.id = id;
            this.name = name;
            this.date = date;
            this.totalEmission = totalEmission;
            this.totalDistance = totalDistance;
            this.totalCost = totalCost;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public LocalDate getDate() {
            return date;
        }

        public Float getTotalEmission() {
            return totalEmission;
        }

        public Float getTotalDistance() {
            return totalDistance;
        }

        public Float getTotalCost() {
            return totalCost;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            JourneyDto entity = (JourneyDto) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.name, entity.name) &&
                    Objects.equals(this.date, entity.date) &&
                    Objects.equals(this.totalEmission, entity.totalEmission) &&
                    Objects.equals(this.totalDistance, entity.totalDistance) &&
                    Objects.equals(this.totalCost, entity.totalCost);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, date, totalEmission, totalDistance, totalCost);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "name = " + name + ", " +
                    "date = " + date + ", " +
                    "totalEmission = " + totalEmission + ", " +
                    "totalDistance = " + totalDistance + ", " +
                    "totalCost = " + totalCost + ")";
        }
    }

    public static class FuelDto implements Serializable {
        private final Integer id;
        @Size(max = 45)
        @NotNull
        private final String name;

        public FuelDto(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FuelDto entity = (FuelDto) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.name, entity.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "name = " + name + ")";
        }
    }

    public static class TransportationDto implements Serializable {
        private final Integer id;
        @Size(max = 45)
        @NotNull
        private final String name;

        public TransportationDto(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TransportationDto entity = (TransportationDto) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.name, entity.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "name = " + name + ")";
        }
    }
}
