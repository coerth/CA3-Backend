package facades;

import javax.persistence.EntityManagerFactory;

public class ProfileFacade 
{
    private static EntityManagerFactory emf;
    private static ProfileFacade instance;

    public ProfileFacade() {
    }

    public static ProfileFacade getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new ProfileFacade();
        }
        return instance;
    }

}
