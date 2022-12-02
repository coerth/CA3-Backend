package facades;

import dtos.JourneyDto;
import dtos.ProfileDto;
import dtos.TransportationDto;
import entities.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransportationFacadeTest {

    private static EntityManagerFactory emf;
    private static TransportationTypeFacade transportationTypeFacade;

    Transportation transportation1;

    public TransportationFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        transportationTypeFacade = transportationTypeFacade.getInstance(emf);
    }

    @BeforeEach
    public void setup() {
        EntityManager em = emf.createEntityManager();

        transportation1 = new Transportation("Løb");

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Transportation.deleteAllRows").executeUpdate();
            em.persist(transportation1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    @Test
    void getTransportationTypeById() {
        TransportationDto result = transportationTypeFacade.getTransportationTypeById(transportation1.getId());

        assertEquals(transportation1.getName(), result.getName());
    }

    @Test
    void getAllTranportationType() {
        int expected = 1;
        List<TransportationDto> transportationDtos = transportationTypeFacade.getAllTranportationType();
        assertEquals(expected, transportationDtos.size());


    }
}
