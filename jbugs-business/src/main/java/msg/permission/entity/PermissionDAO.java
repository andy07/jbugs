package msg.permission.entity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * The DAO for the Permissions Entities.
 *
 * @author msg systems AG;
 * @since 19.1.2
 */
@Stateless
public class PermissionDAO {

    @PersistenceContext(unitName="jbugs-persistence")
    private EntityManager em;

    /**
     * Persists a permission entity.
     *
     * @param p the input entity to be saved.
     * @return the persisted entity.
     */
    public PermissionEntity createPermission(PermissionEntity p) {
        em.persist(p);
        return p;
    }

    public PermissionEntity updatePermission(PermissionEntity p){
        em.merge(p);
        return p;
    }

    public void removePermission(PermissionEntity p) {
        em.remove(p);
    }

    public List<PermissionEntity> getAll(){
        return em.createNamedQuery(PermissionEntity.PERMISSION_FIND_ALL,PermissionEntity.class).getResultList();
    }

}
