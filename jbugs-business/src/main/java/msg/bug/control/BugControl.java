package msg.bug.control;

import msg.bug.BugStatus;
import msg.bug.entity.BugDAO;
import msg.bug.entity.BugEntity;
import msg.bug.entity.dto.BugConverter;
import msg.bug.entity.dto.BugDTO;

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

    public List<BugDTO> getAll() {
        return dao.getAll()
                .stream()
                .map(converter::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public Set<BugStatus> getStatusAllowed(String status) {
        return BugStatus.getNextStatusAllowedList(status);
    }


    public BugDTO save(BugDTO dto) {
        BugEntity entity = converter.convertDTOToEntity(dto);
        entity = dao.save(entity);
        return converter.convertEntityToDTO(entity);
    }

    public BugDTO update(BugDTO dto) {
        BugEntity entity = converter.convertDTOToEntity(dto);
        entity = dao.update(entity);
        return converter.convertEntityToDTO(entity);
    }

    public BugDTO getBugByTitle(String title) {
        BugEntity entity = dao.findBugByTitle(title);
        return converter.convertEntityToDTO(entity);
    }

    public boolean countActiveBugsForUser(String username) {
       return dao.countActiveBugsForUser(username);
    }
}
