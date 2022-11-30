package facades;

import dtos.JourneyDto;
import dtos.ProfileDto;
import entities.Journey;
import entities.Profile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class JourneyFacade {

    private static EntityManagerFactory emf;
    private static JourneyFacade instance;

    public JourneyFacade() {
    }
    public static JourneyFacade getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new JourneyFacade();
        }
        return instance;
    }

    public JourneyDto createJourney (JourneyDto journeyDto) {
        EntityManager em = emf.createEntityManager();

        Profile profile = em.find(Profile.class, journeyDto.getProfile().getId());
        Journey journey = new Journey(journeyDto);
        journey.setProfile(profile);

        try {
            em.getTransaction().begin();
            em.persist(journey);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }

        return new JourneyDto(journey);
    }

    public boolean deleteJourney (int id) {
        EntityManager em = emf.createEntityManager();
        Journey journey = em.find(Journey.class, id);

        try {
            em.getTransaction().begin();
            em.remove(journey);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
        if ( journey == null) {
            return true;
        }
        return false;
    }

    public JourneyDto updateJourney (JourneyDto journeyDto) {
        EntityManager em = emf.createEntityManager();
        Journey journey = new Journey(journeyDto);

        try
        {
            em.getTransaction().begin();
            em.merge(journey);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
        return new JourneyDto(journey);
    }

    public JourneyDto getJourneyById(int id) {
        EntityManager em = emf.createEntityManager();
        Journey j = em.find(Journey.class, id);
        return new JourneyDto(j);
    }
}
