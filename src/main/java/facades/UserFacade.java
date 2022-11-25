package facades;

import entities.Profile;
import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import security.errorhandling.AuthenticationException;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    public User createUser(User user)
    {
        EntityManager em = emf.createEntityManager();
        try {
            em.persist(user);
        } finally {
            em.close();
        }
        return user;
    }


    public Boolean deleteUser(String username){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Profile> query = em.createQuery("SELECT p FROM Profile p WHERE p.userName.userName = :userName", Profile.class);
        query.setParameter("userName", username);
        User u = em.find(User.class, username);

        try{
            em.getTransaction().begin();
            em.remove(u);
            em.getTransaction().commit();
            u = em.find(User.class, username);
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
