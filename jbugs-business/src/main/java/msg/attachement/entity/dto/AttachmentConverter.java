package msg.attachement.entity.dto;

import msg.attachement.entity.AttachmentEntity;

/**
 * @author msg systems AG; Silviu-Mihnea Cucuiet.
 * @since 19.1.2
 * day 6/4/2019
 * time 1:00 PM
 */
public class AttachmentConverter {

    public AttachmentEntity convertDTOToEntity(AttachmentDTO dto) {
        return new AttachmentEntity()
                .setFile(dto.getFile());
    }

    public AttachmentDTO convertEntityToDTO(AttachmentEntity entity) {
        return new AttachmentDTO()
                .setFile(entity.getFile());
    }
}
