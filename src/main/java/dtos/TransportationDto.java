package dtos;

import entities.Transportation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link entities.Transportation} entity
 */
public class TransportationDto implements Serializable {

    private  Integer id;
    @Size(max = 45)
    @NotNull
    private  String name;


    public TransportationDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public TransportationDto(Transportation transportation){
        this.id = transportation.getId();
        this.name = transportation.getName();
    }

    public static List<TransportationDto> getDtos(List<Transportation> transportationList) {
        List<TransportationDto> transportationDtos = new ArrayList();
        transportationList.forEach(transportation -> transportationDtos.add(new dtos.TransportationDto(transportation)));
        return transportationDtos;
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