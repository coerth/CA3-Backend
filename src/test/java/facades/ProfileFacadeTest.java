package facades;

import dtos.ProfileDto;
import entities.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileFacadeTest {

    private static EntityManagerFactory emf;
    private static ProfileFacade profileFacade;

    User u1, u2;
    Profile p1;

    Journey j1, j2, j3;

    JourneyType jt1;


    public ProfileFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        profileFacade = ProfileFacade.getInstance(emf);
    }

    @BeforeEach
    public void setup() {
        EntityManager em = emf.createEntityManager();

        u1 = new User("John", "123");
        u2 = new User("Bertha", "prop");
        p1 = new Profile(1,"a@a.dk", "name", u1);
        jt1 = new JourneyType("recourring");
        j1 = new Journey("Work", LocalDate.of(2022,11,10),200F, 20F, 2F, jt1);
        j2 = new Journey("Work", LocalDate.of(2022,11,10),200F, 20F, 2F, jt1);
        j3 = new Journey("Work", LocalDate.of(2022,11,10),200F, 20F, 2F, jt1);

        LinkedHashSet journeys = new LinkedHashSet<Journey>();
        journeys.add(j1);
        journeys.add(j2);
        journeys.add(j3);

        p1 = new Profile(1,"a@a.dk", "name",  u1);
        j1.setProfile(p1);
        j2.setProfile(p1);
        j3.setProfile(p1);
        p1.setJourneys(journeys);

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Trip.deleteAllRows").executeUpdate();
            em.createNamedQuery("Fuel.deleteAllRows").executeUpdate();
            em.createNamedQuery("Transportation.deleteAllRows").executeUpdate();
            em.createNamedQuery("Journey.deleteAllRows").executeUpdate();
            em.createNamedQuery("JourneyType.deleteAllRows").executeUpdate();
            em.createNamedQuery("Profile.deleteAllRows").executeUpdate();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.persist(u1);
            em.persist(u2);
            em.persist(p1);
            em.persist(jt1);
            em.persist(j1);
            em.persist(j2);
            em.persist(j3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    @Test
    void createProfileTest()
    {

        ProfileDto newProfile = new ProfileDto( "morten@koksikoden.dk", "Morten",new ProfileDto.UserDto("Morten", "123"));
        ProfileDto result = profileFacade.createProfile(newProfile);

        assertNotNull(result);
        assertEquals(newProfile.getName(), result.getName());
    }

    @Test
    void deleteUsertest() {
        boolean response = profileFacade.deleteProfile(p1.getId());
        assertEquals(true, response);
    }

    @Test
    void getAll() {
        int expected = 3;
        List<ProfileDto.JourneyDto> journeyDtos = profileFacade.getAllJourneys(p1.getId());
        assertEquals(expected, journeyDtos.size());
    }

    void updateProfileTest()
    {
        ProfileDto newProfile = new ProfileDto( "niels@koksikoden.dk", "Niels",new ProfileDto.UserDto(u1));
        newProfile.setId(p1.getId());
        profileFacade.updateProfile(newProfile);
        ProfileDto result = profileFacade.getProfileById(p1.getId());

        assertEquals(newProfile.getName(), result.getName());

    }

    @Test
    void getByIdProfile()
    {
        ProfileDto result = profileFacade.getProfileById(p1.getId());

        assertEquals(p1.getName(), result.getName());
    }

}
