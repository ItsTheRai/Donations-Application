import com.gp225.securityexercise2.ejb.QuerryService;
import com.gp225.securityexercise2.ejb.RegistrationServiceBean;
import com.gp225.securityexercise2.entity.SystemUser;
import com.gp225.securityexercise2.entity.SystemUserGroup;
import com.gp225.securityexercise2.entity.systemusers.Admin;
import com.gp225.securityexercise2.ejb.exceptions.DuplicateUsernameException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Startup;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.DatatypeConverter;

@Singleton
@Startup
public class LifecycleBean {
    @Inject
    QuerryService queryService;
    @PersistenceContext
    EntityManager em;

    public LifecycleBean(){
        
    }
  @PostConstruct
  public void init() {
      
    /* Startup stuff here. */
    String name="admin1";
      String password ="admin1";
            SystemUser sys_u;
            Admin sys_user;
            SystemUserGroup sys_user_group;
            String paswdToStoreInDB = null;
        try {
            paswdToStoreInDB = DatatypeConverter.printHexBinary(MessageDigest.getInstance("SHA-256").
                    digest(password.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(LifecycleBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        //create objects
        
            //create objects
        
            sys_u = new SystemUser(name, paswdToStoreInDB);
            
            sys_user = new Admin(name, paswdToStoreInDB);
            sys_user_group = new SystemUserGroup(name, "admins");
            //persiists data in database
            em.persist(sys_u);
            em.persist(sys_user);
            em.persist(sys_user_group);
  }

  @PreDestroy
  public void destroy() {
      em.close();
    /* Shutdown stuff here */
  }
}