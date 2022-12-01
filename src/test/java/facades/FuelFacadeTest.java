package facades;

import dtos.FuelDto;
import entities.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuelFacadeTest {
    private static EntityManagerFactory emf;
    private static FuelFacade fuelFacade;

    Fuel f1, f2;


    public FuelFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        fuelFacade = FuelFacade.getInstance(emf);
    }

    @BeforeEach
    public void setup() {
        EntityManager em = emf.createEntityManager();
        f1 = new Fuel("Rugbrødsmotor");
        f2 = new Fuel("Diesel");

        try {
            em.getTransaction().begin();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.persist(f1);
            em.persist(f2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    void getAll() {
        int expected = 2;
        List<FuelDto> fuelDtos = fuelFacade.getAllFuel();
        assertEquals(expected, fuelDtos.size());
    }

    @Test
    void getFuelById() {
        FuelDto result = fuelFacade.getFuelById(f1.getId());

        assertEquals(f1.getName(), result.getName());

    }

}
