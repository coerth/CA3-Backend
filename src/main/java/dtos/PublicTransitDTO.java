package dtos;

import java.util.Objects;

public class PublicTransitDTO {

    private final Integer distance;

    private final String type;

    public PublicTransitDTO(Integer distance, String type) {
        this.distance = distance;
        this.type = type;
    }

    public Integer getDistance() {
        return distance;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PublicTransitDTO)) return false;
        PublicTransitDTO that = (PublicTransitDTO) o;
        return getDistance().equals(that.getDistance()) && getType().equals(that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDistance(), getType());
    }

    @Override
    public String toString() {
        return "PublicTransitDTO{" +
                "distance=" + distance +
                ", type='" + type + '\'' +
                '}';
    }
}
