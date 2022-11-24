package facades;

import entities.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserFacadeTest {

    private static EntityManagerFactory emf;
    private static UserFacade userFacade;

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
    void verifyPassword() throws AuthenticationException {
        String passExpected = "123";
        User user = userFacade.getVeryfiedUser("John", "123");
        System.out.println(user.getUserPass());
        String returnedPass = user.getUserPass();
        boolean decryptPass = BCrypt.checkpw(passExpected,returnedPass);
        assertTrue(decryptPass);

    }
}
