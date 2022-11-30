package dtos;

import entities.Journey;
import entities.JourneyType;
import entities.Transportation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link entities.JourneyType} entity
 */
public class JourneyTypeDto implements Serializable {
    private final Integer id;
    @Size(max = 45)
    @NotNull
    private final String name;

    public JourneyTypeDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public JourneyTypeDto(JourneyType journeyType){
        this.id = journeyType.getId();
        this.name = journeyType.getName();
    }

    public static List<JourneyTypeDto> getDtos(List<JourneyType> journeyTypeList) {
        List<JourneyTypeDto> journeyTypeDtos = new ArrayList();
        journeyTypeList.forEach(journeyType -> journeyTypeDtos.add(new dtos.JourneyTypeDto(journeyType)));
        return journeyTypeDtos;
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
        JourneyTypeDto entity = (JourneyTypeDto) o;
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