package facades;


import dtos.ProfileDto;
import entities.Profile;
import entities.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileFacadeTest {

    private static EntityManagerFactory emf;
    private static ProfileFacade profileFacade;

    User u1, u2;
    Profile p1;


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

        try {
            em.getTransaction().begin();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.persist(u1);
            em.persist(u2);
            em.persist(p1);
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
