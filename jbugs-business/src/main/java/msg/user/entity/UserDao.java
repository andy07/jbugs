package msg.user.entity;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
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
        return em.createNamedQuery(UserEntity.USER_FIND_BY_EMAIL, UserEntity.class)
                .setParameter(UserEntity.EMAIL,email)
                .getSingleResult();
    }

    public UserEntity findByUsername(String username){
        UserEntity userEntity=null;
                try{
                    userEntity=em.createNamedQuery(UserEntity.USER_FIND_BY_USERNAME, UserEntity.class)
                            .setParameter(UserEntity.USERNAME,username)
                            .getSingleResult();
                    //todo mai multe username-uri la fel
                    //todo nu exista username-ul respectiv in baza de date
                }catch (Exception e ){
                    e.printStackTrace();
                }
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
        UserEntity current = findByUsername(user.getUsername());
        user.setId(current.getId());
        user = em.merge(user);
        return user;
    }

    public boolean existsUsername(String username){
        long count = em.createNamedQuery(UserEntity.USER_COUNT_BY_USERNAME, Long.class)
                .setParameter(UserEntity.USERNAME,username)
                .getSingleResult();
        return (count > 0);
    }

    public List<UserEntity> findUsernames(String username){
        //List<UserEntity>userEntities = em.createNamedQuery(UserEntity.FIND_USERNAMES, Long.class)
        //        .setParameter(UserEntity.USERNAME,username+"%")
         //       .getResultList();

        TypedQuery<UserEntity> query = em.createQuery("SELECT u from UserEntity u where u.username like :" + UserEntity.USERNAME,UserEntity.class);
        query.setParameter("username", username+"%");
        return query.getResultList();
    }


    public List<UserEntity> getAll() {
        return em.createNamedQuery(UserEntity.USER_FIND_ALL, UserEntity.class).getResultList();
    }

    public List<String> getUsers() {
        List<String> result = new ArrayList<>();
        List<UserEntity> users = em.createNamedQuery(UserEntity.USER_FIND_ALL, UserEntity.class).getResultList();

        for (int i = 0; i < users.size(); i++) {
            result.add(users.get(i).getUsername());
        }
        return result;

    }
}
