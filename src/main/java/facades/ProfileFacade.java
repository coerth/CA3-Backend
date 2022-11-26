package facades;

import dtos.ProfileDto;
import entities.Profile;
import entities.User;

import javax.persistence.EntityManager;
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

    public ProfileDto createProfile(ProfileDto profileDto)
    {

        EntityManager em = emf.createEntityManager();
        User user = new User(profileDto.getUserDto());
        Profile profile = new Profile(profileDto, user);
        user.setProfile(profile);
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();

        } finally {
            em.close();
        }

        System.out.println(profile);
        return new ProfileDto(profile);
    }
}
