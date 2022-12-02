package facades;

import entities.Profile;
import entities.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class UserFacadeTest {

    private static EntityManagerFactory emf;
    private static UserFacade userFacade;

    User u1, u2;

    public UserFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        userFacade = UserFacade.getUserFacade(emf);
    }

    @BeforeEach
    public void setup() {
        EntityManager em = emf.createEntityManager();

        u1 = new User("John", "123");
        u2 = new User("Bertha", "prop");

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
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    void verifyPassword() throws AuthenticationException {
        String passExpected = "123";
        User user = userFacade.getVeryfiedUser("John", "123");
        System.out.println(user.getUserPass());
        String returnedPass = user.getUserPass();
        boolean decryptPass = BCrypt.checkpw(passExpected,returnedPass);
        assertTrue(decryptPass);

    }

    @Test
    void createUserTest()
    {
        User newUser = new User("Denis", "Denis123");
        User result = userFacade.createUser(newUser);

        assertNotNull(result);
    }
}
