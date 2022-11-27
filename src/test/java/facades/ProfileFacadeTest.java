package facades;


import dtos.ProfileDto;
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
    private static ProfileFacade ProfileFacade;

    public ProfileFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        ProfileFacade = ProfileFacade.getInstance(emf);
    }

    @BeforeEach
    public void setup() {
        EntityManager em = emf.createEntityManager();

        User u1 = new User("John", "123");
        User u2 = new User("Bertha", "prop");

        try {
            em.getTransaction().begin();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.persist(u1);
            em.persist(u2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    @Test
    void createProfileTest()
    {

        ProfileDto newProfile = new ProfileDto( "morten@koksikoden.dk", "Morten",new ProfileDto.UserDto("Morten", "123"));
        ProfileDto result = ProfileFacade.createProfile(newProfile);

        assertNotNull(result);
        assertEquals(newProfile.getName(), result.getName());
    }

}
