package msg.bug.boundary;

import msg.bug.BugStatus;
import msg.bug.control.BugControl;
import msg.bug.entity.dto.BugDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Set;

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

    public List<BugDTO> getAll() {
        return control.getAll();
    }

    public Set<BugStatus> getStatusAllowed(String status) {
        return BugStatus.getNextStatusAllowedList(status);
    }

    public String getNoBugsByStatus(String status){
        return String.valueOf(control.getNoBugsByStatus(status));
    }


    public BugDTO save(BugDTO dto) {
        return control.save(dto);
    }

    public BugDTO update(BugDTO dto) {
        System.out.println(dto);
        return control.update(dto);
    }

    /*public BugDTO getBugByTitle(String title) {
        return control.getBugByTitle(title);
    }*/
    public BugDTO getBugById(long id) {
        return control.getBugById(id);
    }
    public boolean countActiveBugsForUser(String username){
        return control.countActiveBugsForUser(username);
    }

    public BugDTO getBugByTitle(String title) {
        return control.getBugByTitle(title);
    }
}
