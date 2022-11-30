package dtos;

import entities.Fuel;
import entities.Transportation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link entities.Fuel} entity
 */
public class FuelDto implements Serializable {
    private final Integer id;
    @Size(max = 45)
    @NotNull
    private final String name;

    public FuelDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public FuelDto(Fuel fuel){
        this.id = fuel.getId();
        this.name = fuel.getName();
    }

    public static List<FuelDto> getDtos(List<Fuel> fuelList) {
        List<FuelDto> fuelDtos = new ArrayList();
        fuelList.forEach(fuel -> fuelDtos.add(new dtos.FuelDto(fuel)));
        return fuelDtos;
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