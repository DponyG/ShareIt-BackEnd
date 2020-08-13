package shareit;

import static org.junit.Assert.*;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.shareit.entity.jpa.JpaAccount;
import com.shareit.entityinterface.AccountManager;



@RunWith(Arquillian.class)
public class JPATest {
    
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    AccountManager accountManager;
    
    
    @Deployment
    public static Archive<?> createDeploymentPackage() {
        return ShrinkWrap.create(WebArchive.class, "UserPersistenceTest.war")
                .addPackage(AccountManager.class.getPackage())
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
}

    @Test
    public void assertNull() {
        assertNotNull(em);
        assertNotNull(accountManager);
    }
    
    @Test
    public void checkInsertUserManager() {
    	accountManager.addUser("Test");
    //	assert(userManager.userExists("Test"));   	
    }
    
    @Test
    public void assertAllEmployee() {
    	Collection<JpaAccount> allUsers = accountManager.getAllUsers();
    	System.out.println(allUsers.size());
    }

}
