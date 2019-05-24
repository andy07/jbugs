package msg.user.entity;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;

/**
 * The DAO for the User Entities.
 *
 * @author msg systems AG;
 * @since 19.1.2
 */
@Stateless
public class UserDao {

    @PersistenceContext(unitName="jbugs-persistence")
    private EntityManager em;

    /**
     * Checks if a email address of a user is in use.
     *
     * @param email the email to check for. mandatory
     * @return <code>true</code> if the input email is associated with a user.
     */
    public boolean existsEmail(String email){
        long count = em.createNamedQuery(UserEntity.USER_COUNT_BY_EMAIL, Long.class)
                .setParameter(UserEntity.EMAIL,email)
                .getSingleResult();
        return (count > 0);
    }

    public UserEntity findUserByEmail(String email){
        UserEntity userEntity = em.createNamedQuery(UserEntity.USER_FIND_BY_EMAIL, UserEntity.class)
                .setParameter(UserEntity.EMAIL,email)
                .getSingleResult();
        return userEntity;
    }

    public UserEntity findByUsername(String username){
        UserEntity userEntity=em.createNamedQuery(UserEntity.USER_FIND_BY_USERNAME, UserEntity.class)
                .setParameter(UserEntity.USERNAME,username)
                .getSingleResult();
        return userEntity;
    }


    /**
     * Persists a user entity.
     *
     * @param user the input entity to be saved.
     * @return the persisted entity.
     */
    public UserEntity createUser(UserEntity user){
        em.persist(user);
        return user;
    }
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public UserEntity updateUser(UserEntity user){
        em.merge(user);
        return user;
    }

    public List<UserEntity> getAll() {
        return em.createNamedQuery(UserEntity.USER_FIND_ALL,UserEntity.class).getResultList();
    }

}
