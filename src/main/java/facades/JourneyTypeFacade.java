package facades;

import dtos.JourneyTypeDto;
import entities.JourneyType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class JourneyTypeFacade {

    private static EntityManagerFactory emf;
    private static JourneyTypeFacade instance;

    public JourneyTypeFacade() {
    }

    public static JourneyTypeFacade getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new JourneyTypeFacade();
        }
        return instance;
    }

    public JourneyTypeDto getJourneyTypeById(int id){
        EntityManager em = emf.createEntityManager();
        JourneyType journeyType = em.find(JourneyType.class, id);

        return new JourneyTypeDto(journeyType);
    }

    public List<JourneyTypeDto> getAllJourneyType(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<JourneyType> query = em.createQuery("SELECT j FROM JourneyType j", JourneyType.class);
        List<JourneyType> journeyTypeList = query.getResultList();
        return JourneyTypeDto.getDtos(journeyTypeList);
    }
}
