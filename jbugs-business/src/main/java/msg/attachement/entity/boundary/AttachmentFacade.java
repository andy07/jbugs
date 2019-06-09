package msg.attachement.entity.boundary;

import msg.attachement.entity.AttachmentEntity;
import msg.attachement.entity.control.AttachmentControl;
import msg.attachement.entity.dto.AttachmentConverter;
import msg.attachement.entity.dto.AttachmentDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */

@Stateless
public class AttachmentFacade {

    @EJB
    private AttachmentControl attachmentControl;

    public void saveAll(List<AttachmentDTO> dtos) {
        dtos.forEach(dto -> {
            attachmentControl.save(dto);
        });
    }

    public List<AttachmentDTO> getAllForBug(long bug_id){
        return this.attachmentControl.getAllForBug(bug_id);
    }
}
