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
        final BugEntity entity = new BugEntity();
        entity
                .setAssignedTo(dto.getAssignedTo())
                .setTitle(dto.getTitle())
                .setVersion(dto.getVersion())
                .setTargetDate(dto.getTargetDate())
                .setStatus(dto.getStatus())
                .setFixedVersion(dto.getFixedVersion())
                .setSeverity(dto.getSeverity());

        if (dto.getDescription().isPresent()) {
            entity.setDescription(dto.getDescription().get());

        }
        if (dto.getCreatedBy().isPresent()) {
            entity.setCreatedBy(dto.getCreatedBy().get());
        }
        return entity;

    }

}
