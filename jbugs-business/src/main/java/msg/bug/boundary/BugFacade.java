package msg.bug.boundary;

import msg.bug.control.BugControl;
import msg.bug.entity.dto.BugOutputDTO;

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
public class BugFacade {

    @EJB
    private BugControl control;

    public List<BugOutputDTO> getAll() {
        return control.getAll();
    }
}
