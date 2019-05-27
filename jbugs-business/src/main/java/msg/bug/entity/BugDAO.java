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
}
