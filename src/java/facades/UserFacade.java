package facades;

import deploy.DeploymentConfiguration;
import entity.Role;
import entity.User;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import security.PasswordHash;

public class UserFacade {

  EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

  public UserFacade() {
   
  }
  
  public Boolean newUser (String userName, String password, String roleName )
  {
      EntityManager em = emf.createEntityManager();
      
      try 
      {
      User user =  em.find(User.class, userName);
          
        if(user != null)
        {
            return false;
        }
        else
        {
            System.out.println("I'm here");
            String pass;
            User newUser = new User(userName, PasswordHash.createHash(password));
            try
            {
            newUser.AddRole(em.find(Role.class, roleName));
            }
            catch (Exception e)
            {
                return false;
            }
            
            
            em.getTransaction().begin();
            em.persist(newUser);
            
            em.getTransaction().commit();
            em.close();
            return true;
        }
      }
      catch (Exception e)
      {
        return false;      
      }

  }

  public User deleteUser(String userName){
      EntityManager em = emf.createEntityManager();
      User user = em.find(User.class, userName);
      try {
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
        } finally {
            em.close();
        }
      return user;      
  }
  
  public List<User> getUsers(){
      EntityManager em = emf.createEntityManager();
      return em.createQuery("Select s from User s").getResultList();
  }
  
  public User getUserByUserId(String id) {
    EntityManager em = emf.createEntityManager();
    try {
      return em.find(User.class, id);
    } finally {
      em.close();
    }
  }
  /*
   Return the Roles if users could be authenticated, otherwise null
   */
  public List<String> authenticateUser(String userName, String password) {
    EntityManager em = emf.createEntityManager();
    try {
      User user =  em.find(User.class, userName);
      if(user == null){
        return null;
      }
      try {
        boolean authenticated = PasswordHash.validatePassword(password, user.getPassword());
        return authenticated ? user.getRolesAsStrings() : null;
      } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
        Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        return null;
      }
      
    } finally {
      em.close();
    }
    
  }

}
