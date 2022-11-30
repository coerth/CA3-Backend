package facades;

import dtos.ProfileDto;
import dtos.TransportationDto;
import entities.Transportation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class TransportationTypeFacade {

    private static EntityManagerFactory emf;
    private static TransportationTypeFacade instance;

    private TransportationTypeFacade(){

    }

    public static TransportationTypeFacade getInstance(EntityManagerFactory _emf){
        if (instance == null) {
            emf = _emf;
            instance = new TransportationTypeFacade();
        }
        return instance;
    }

    public TransportationDto getTransportationTypeById(int id){
        EntityManager em = emf.createEntityManager();
        Transportation transportation = em.find(Transportation.class, id);

        return new TransportationDto(transportation);
    }

    public List<TransportationDto> getAllTranportationType(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Transportation> query = em.createQuery("SELECT t FROM Transportation t", Transportation.class);
        List<Transportation> transportation = query.getResultList();
        return TransportationDto.getDtos(transportation);
    }

}
