package entities;

import dtos.ProfileDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NamedQuery(name="Fuel.deleteAllRows",query = "DELETE from Fuel")
@Table(name = "fuel")
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @OneToMany(mappedBy = "fuel")
    private Set<Trip> trips = new LinkedHashSet<>();

    public Fuel() {
    }

    public Fuel(ProfileDto.JourneyDto.TripDto.FuelDto fuelDto) {
        this.id = fuelDto.getId();
        this.name = fuelDto.getName();
    }

    public Fuel(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Trip> getTrips() {
        return trips;
    }

    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }

}