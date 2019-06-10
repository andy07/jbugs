package msg.attachement.entity.dto;

import msg.attachement.entity.AttachmentEntity;

import javax.ejb.Stateless;

/**
 * @author msg systems AG; Silviu-Mihnea Cucuiet.
 * @since 19.1.2
 * day 6/4/2019
 * time 1:00 PM
 */
@Stateless
public class AttachmentConverter {

    public AttachmentEntity convertDTOToEntity(AttachmentDTO dto) {
        return new AttachmentEntity()
                .setId(dto.getId())
                .setFile(dto.getFile().getBytes())
                .setName(dto.getName())
                .setType(dto.getType())
                .setBugId(dto.getBugId());
    }

    public AttachmentDTO convertEntityToDTO(AttachmentEntity entity) {
        return new AttachmentDTO()
                .setId(entity.getId())
                .setFile(new String(entity.getFile()))
                .setName(entity.getName())
                .setType(entity.getType());
    }
}
