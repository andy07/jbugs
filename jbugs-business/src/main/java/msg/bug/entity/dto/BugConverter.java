package msg.bug.entity.dto;

import msg.bug.control.BugControl;
import msg.bug.entity.BugEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    private java.sql.Date convertDTODateToSQLDate(String targetDate) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(targetDate);
            java.sql.Date sDate = new java.sql.Date(date.getTime());
            return sDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return new java.sql.Date(new Date().getTime());
        }

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
