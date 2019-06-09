package msg.attachement.entity.control;

import msg.attachement.entity.dto.AttachmentConverter;
import msg.attachement.entity.dto.AttachmentDAO;
import msg.attachement.entity.dto.AttachmentDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */

@Stateless
public class AttachmentControl {
    @EJB
    private AttachmentDAO dao;

    @EJB
    private AttachmentConverter attachmentConverter;

    public void save(AttachmentDTO dto) {
        dao.save(attachmentConverter.convertDTOToEntity(dto));
    }

    public List<AttachmentDTO> getAllForBug(long bug_id){
        return dao.getAllForBug(bug_id)
                .stream()
                .map(attachmentConverter::convertEntityToDTO)
                .collect(Collectors.toList());
    }
}
