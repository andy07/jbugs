package msg.attachement.entity.dto;

import msg.attachement.entity.AttachmentEntity;
import msg.bug.entity.BugEntity;

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
public class AttachmentDAO {

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager em;


    public void save(AttachmentEntity entity) {
        em.persist(entity);
    }

    public List<AttachmentEntity> getAllForBug(long bug_id) {
        return em.createNamedQuery(AttachmentEntity.ATTACHEMENT_FIND_ALL, AttachmentEntity.class)
                .setParameter("bugId", bug_id)
                .getResultList();
    }

    public void deleteAttachment(long id) {
        em.createNamedQuery(AttachmentEntity.ATTACHEMENT_DELETE)
                .setParameter("id", id)
                .executeUpdate();
    }

}
