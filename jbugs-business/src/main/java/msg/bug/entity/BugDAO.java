package msg.bug.entity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */

@Stateless
public class BugDAO {

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager em;

    public List<BugEntity> getAll() {
        return em.createNamedQuery(BugEntity.BUG_FIND_ALL, BugEntity.class).getResultList();
    }

    public BugEntity save(BugEntity entity) {
        em.persist(entity);
        return entity;
    }

    public BugEntity update(BugEntity entity) {
        //BugEntity current = findBugByTitle(entity.getTitle());
        //BugEntity current = findBugById(entity.getId());
        //entity.setId(current.getId());
        entity = em.merge(entity);
        //em.flush();
        return entity;
    }

    public BugEntity findBugByTitle(String title) {
        return em.createNamedQuery(BugEntity.BUG_FIND_BY_TITLE, BugEntity.class)
                .setParameter(BugEntity.TITLE, title)
                .getSingleResult();
    }

    public boolean countActiveBugsForUser(String username) {
        long count = em.createNamedQuery(BugEntity.COUNT_ACTIVE_BUGS_FOR_USER, Long.class)
                .setParameter(BugEntity.STATUS, "CLOSED")
                .setParameter(BugEntity.USER,username)
                .getSingleResult();
        return (count > 0);
    }

    public BugEntity findBugById(long id) {
        return em.createNamedQuery(BugEntity.BUG_FIND_BY_ID, BugEntity.class)
                .setParameter(BugEntity.ID, id)
                .getSingleResult();
    }

    public Long getNoBugsByStatus(String status){
        long count =  em.createNamedQuery(BugEntity.COUNT_BUGS_BY_STATUS, Long.class)
                .setParameter(BugEntity.STATUS, status)
                .getSingleResult();
        return count;
    }
}
