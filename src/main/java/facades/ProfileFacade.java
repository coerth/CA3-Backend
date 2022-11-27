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
        User user = new User(profileDto.getUser());
        Profile profile = new Profile(profileDto, user);

        try {
            em.getTransaction().begin();
            em.persist(profile);
            em.getTransaction().commit();

        } finally {
            em.close();
        }

        System.out.println(profile);
        return new ProfileDto(profile);
    }

    public Boolean deleteProfile(int id){
        EntityManager em = emf.createEntityManager();
        Profile profile = em.find(Profile.class, id);
        User u;

        try{
            em.getTransaction().begin();
            em.remove(profile);
            em.getTransaction().commit();
            u = em.find(User.class, profile.getUser().getId());
        } finally {
            em.close();
        }

        if(u == null)
        {
            return true;
        }
        return false;
    }
}
