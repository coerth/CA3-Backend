package facades;

import dtos.FuelDto;
import entities.Fuel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class FuelFacade {

    private static EntityManagerFactory emf;
    private static FuelFacade instance;

    UserFacade userFacade = UserFacade.getUserFacade(emf);

    public FuelFacade() {
    }

    public static FuelFacade getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new FuelFacade();
        }
        return instance;
    }

    public FuelDto getFuelById(int id){
        EntityManager em = emf.createEntityManager();
        Fuel fuel = em.find(Fuel.class, id);

        return new FuelDto(fuel);
    }

    public List<FuelDto> getAllFuel(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Fuel> query = em.createQuery("SELECT f FROM Fuel f", Fuel.class);
        List<Fuel> fuelList = query.getResultList();
        return FuelDto.getDtos(fuelList);
    }
}
