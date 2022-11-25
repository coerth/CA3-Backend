package dtos;

public class CombinedTransportTypeDTO {

    private CarTravelDTO carTravelDTO;

    private PublicTransitDTO publicTransitDTO;

    public CombinedTransportTypeDTO(CarTravelDTO carTravelDTO, PublicTransitDTO publicTransitDTO) {
        this.carTravelDTO = carTravelDTO;
        this.publicTransitDTO = publicTransitDTO;
    }

    public CarTravelDTO getCarTravelDTO() {
        return carTravelDTO;
    }

    public void setCarTravelDTO(CarTravelDTO carTravelDTO) {
        this.carTravelDTO = carTravelDTO;
    }

    public PublicTransitDTO getPublicTransitDTO() {
        return publicTransitDTO;
    }

    public void setPublicTransitDTO(PublicTransitDTO publicTransitDTO) {
        this.publicTransitDTO = publicTransitDTO;
    }
}
