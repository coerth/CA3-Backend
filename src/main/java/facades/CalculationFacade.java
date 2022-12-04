package facades;

import com.google.gson.Gson;
import dtos.CarTravelDTO;
import dtos.CombinedTransportTypeDTO;
import dtos.JourneyDto;
import dtos.PublicTransitDTO;
import entities.Journey;
import utils.HttpUtils;

import javax.persistence.EntityManagerFactory;

public class CalculationFacade {

    private static EntityManagerFactory emf;

    private static CalculationFacade instance;

    Gson gson = new Gson();

    public CalculationFacade() {

    }

    public static CalculationFacade getCalculationFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CalculationFacade();
        }
        return instance;
    }

    public String getAPIData() {
        String carTravelURL = HttpUtils.fetchAPIData("https://app.trycarbonapi.com/api/carTravel", "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiMjUzMGM4ZmIyMTdlYmJiYjg3ZjgwMDdjNDZjYTc5ODMwZjQxNzgzZDVhZTExNTUwMTA4ODdjMzY1NGRlMWNiNDI4YTc2ZGNmMjM3YWFlMGUiLCJpYXQiOjE2NjkzNzA5OTYsIm5iZiI6MTY2OTM3MDk5NiwiZXhwIjoxNzAwOTA2OTk2LCJzdWIiOiIyMzI0Iiwic2NvcGVzIjpbXX0.Ot63eEC6iCdCaea2TKX7DlMgvCpKGM8CfBuMSGivsTOUVerSUyQGUR-SA5e2-5ffN0ATmMavvFtK0f6SgCfETg");
        String publicTransitURL = HttpUtils.fetchAPIData("https://app.trycarbonapi.com/api/publicTransit", "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiMjUzMGM4ZmIyMTdlYmJiYjg3ZjgwMDdjNDZjYTc5ODMwZjQxNzgzZDVhZTExNTUwMTA4ODdjMzY1NGRlMWNiNDI4YTc2ZGNmMjM3YWFlMGUiLCJpYXQiOjE2NjkzNzA5OTYsIm5iZiI6MTY2OTM3MDk5NiwiZXhwIjoxNzAwOTA2OTk2LCJzdWIiOiIyMzI0Iiwic2NvcGVzIjpbXX0.Ot63eEC6iCdCaea2TKX7DlMgvCpKGM8CfBuMSGivsTOUVerSUyQGUR-SA5e2-5ffN0ATmMavvFtK0f6SgCfETg");




        return null;
    }

    public JourneyDto getJourney(JourneyDto journeyDto){
        for(JourneyDto.TripDto tripDto : journeyDto.getTrips()){

        }


        return null;
    }
}
