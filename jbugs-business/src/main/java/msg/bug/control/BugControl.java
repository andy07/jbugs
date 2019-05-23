package msg.bug.control;

import msg.bug.entity.BugDAO;
import msg.bug.entity.dto.BugDTO;

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
public class BugControl {

    @EJB
    private BugDAO dao;

    public List<BugDTO> getAll() {
        return dao.getAll().stream().map(entity -> {
            return new BugDTO();
            // TODO: 5/22/2019 create bug converter and shit
        }).collect(Collectors.toList());
    }
}
