package msg.bug.entity.dto;

import msg.bug.control.BugControl;
import msg.bug.entity.BugEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Document me.
 *
 * @author Alexandra Dragos;
 * @since 19.1.2
 */
@Stateless
public class BugConverter {

    @EJB
    private BugControl bugControl;

    /**
     * Converts a {@link BugDTO} to {@link msg.bug.entity.BugEntity}.
     *
     * @param dto the input dto.
     * @return the output un-managed Entity.
     */
    public BugEntity convertDTOToEntity(BugDTO dto) {
        return new BugEntity()
                .setId(dto.getId())
                .setTitle(dto.getTitle())
                .setVersion(dto.getVersion())
                .setTargetDate(dto.getTargetDate())
                .setStatus(dto.getStatus())
                .setFixedVersion(dto.getFixedVersion())
                .setSeverity(dto.getSeverity())
                .setAssignedTo(dto.getAssignedTo())
                .setDescription(dto.getDescription())
                .setCreatedBy(dto.getCreatedBy());
    }

    public BugDTO convertEntityToDTO(BugEntity entity) {
        return new BugDTO()
                .setId(entity.getId())
                .setTitle(entity.getTitle())
                .setVersion(entity.getVersion())
                .setTargetDate(entity.getTargetDate())
                .setStatus(entity.getStatus())
                .setFixedVersion(entity.getFixedVersion())
                .setSeverity(entity.getSeverity())
                .setAssignedTo(entity.getAssignedTo())
                .setDescription(entity.getDescription())
                .setCreatedBy(entity.getCreatedBy());
    }

}
