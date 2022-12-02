package facades;

import dtos.JourneyTypeDto;
import entities.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JourneyTypeFacadeTest {
    private static EntityManagerFactory emf;
    private static JourneyTypeFacade JourneyTypeFacade;

    JourneyType jt1, jt2;


    public JourneyTypeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        JourneyTypeFacade = JourneyTypeFacade.getInstance(emf);
    }

    @BeforeEach
    public void setup() {
        EntityManager em = emf.createEntityManager();
        jt1 = new JourneyType("Template");
        jt2 = new JourneyType("Recurring");

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Trip.deleteAllRows").executeUpdate();
            em.createNamedQuery("Fuel.deleteAllRows").executeUpdate();
            em.createNamedQuery("Transportation.deleteAllRows").executeUpdate();
            em.createNamedQuery("Journey.deleteAllRows").executeUpdate();
            em.createNamedQuery("JourneyType.deleteAllRows").executeUpdate();
            em.createNamedQuery("Profile.deleteAllRows").executeUpdate();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.persist(jt1);
            em.persist(jt2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    void getAll() {
        int expected = 2;
        List<JourneyTypeDto> JourneyTypeDtos = JourneyTypeFacade.getAllJourneyType();
        assertEquals(expected, JourneyTypeDtos.size());
    }

    @Test
    void getJourneyTypeById() {
        JourneyTypeDto result = JourneyTypeFacade.getJourneyTypeById(jt1.getId());

        assertEquals(jt1.getName(), result.getName());

    }

}
