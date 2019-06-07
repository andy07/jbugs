package msg.bug.control;

import msg.bug.BugStatus;
import msg.bug.entity.BugDAO;
import msg.bug.entity.BugEntity;
import msg.bug.entity.dto.BugConverter;
import msg.bug.entity.dto.BugDTO;
import msg.notifications.boundary.NotificationFacade;
import msg.user.control.UserControl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */

@Stateless
public class BugControl {

    @EJB
    private BugDAO dao;

    @EJB
    private BugConverter converter;

    @EJB
    private NotificationFacade notificationFacade;

    public List<BugDTO> getAll() {
        return dao.getAll()
                .stream()
                .map(converter::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public Set<BugStatus> getStatusAllowed(String status) {
        return BugStatus.getNextStatusAllowedList(status);
    }

    public Long getNoBugsByStatus(String status){
        return dao.getNoBugsByStatus(status);
    }


    public BugDTO save(BugDTO dto) {
        if (this.validateBugInput(dto) == true) {
            BugEntity entity = converter.convertDTOToEntity(dto);
            entity = dao.save(entity);
            notificationFacade.createNewBugNotification(dto.getCreatedBy(), dto.getAssignedTo(), dto);
            return converter.convertEntityToDTO(entity);
        } else {
            return null;
        }
    }

    public BugDTO update(BugDTO dto) {
        if (this.validateBugInput(dto) == true) {
            BugEntity entity = converter.convertDTOToEntity(dto);
            entity = dao.update(entity);
            notificationFacade.createUpdatedBugNotification(dto.getCreatedBy(), dto.getAssignedTo(), dto);
            return converter.convertEntityToDTO(entity);
        } else {
            return null;
        }
    }

    public BugDTO getBugByTitle(String title) {
        BugEntity entity = dao.findBugByTitle(title);
        return converter.convertEntityToDTO(entity);
    }

    public boolean countActiveBugsForUser(String username) {
        return dao.countActiveBugsForUser(username);
    }

    private boolean validateBugInput(BugDTO bugDTO) {
        if (true)
            return true;
        if (bugDTO.getTitle().isEmpty() || bugDTO.getCreatedBy().isEmpty() || bugDTO.getDescription().isEmpty()
                || bugDTO.getTargetDate().toString().isEmpty() || bugDTO.getFixedVersion().isEmpty()
                || bugDTO.getAssignedTo().isEmpty() || bugDTO.getSeverity().isEmpty() || bugDTO.getDescription().isEmpty())
            return false;
        if (!bugDTO.getVersion().matches(" [0-9a-z\\.]*$"))
            return false;
        if (!(bugDTO.getSeverity().equals("HIGH") && bugDTO.getSeverity().equals("LOW")
                && bugDTO.getSeverity().equals("MEDIUM") && bugDTO.getSeverity().equals("CRITICAL")))
            return false;
        if (!(bugDTO.getStatus().equals("NEW") && bugDTO.getStatus().equals("REJECTED")
                && bugDTO.getStatus().equals("IN_PROGRESS") && bugDTO.getStatus().equals("INFO_NEEDED"))
                && bugDTO.getStatus().equals("FIXED") && bugDTO.getStatus().equals("CLOSED"))
            return false;
        if (!(bugDTO.getDescription().length() > 255))
            return false;

        return true;
    }

    public BugDTO getBugById(long id) {
        BugEntity entity = dao.findBugById(id);
        return converter.convertEntityToDTO(entity);
    }
}
