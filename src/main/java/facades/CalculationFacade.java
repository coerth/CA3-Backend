package facades;

import com.google.gson.Gson;
import dtos.JourneyDto;
import utils.HttpUtils;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;

public class CalculationFacade {

    private static EntityManagerFactory emf;

    private static CalculationFacade instance;
    JourneyFacade journeyFacade = JourneyFacade.getInstance(emf);


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

    public JourneyDto getJourney(JourneyDto journeyDto) throws IOException {
        for(JourneyDto.TripDto tripDto : journeyDto.getTrips()){
            tripDto = HttpUtils.getEmission(tripDto);
            journeyDto.setTotalEmission(journeyDto.getTotalEmission() + tripDto.getEmission());
            journeyDto.setTotalDistance(journeyDto.getTotalDistance() + tripDto.getDistance());
        }

        if(journeyDto.getId() != null)
        {
            journeyFacade.updateJourney(journeyDto);
        }
        else
        {
            journeyFacade.createJourney(journeyDto);
        }

        return journeyDto;

    }
}
