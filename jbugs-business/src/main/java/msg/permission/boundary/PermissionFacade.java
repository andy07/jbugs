package msg.permission.boundary;

import msg.permission.control.PermissionControl;
import msg.permission.entity.dto.PermissionDTO;

import javax.ejb.EJB;
import java.util.List;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class PermissionFacade {

    @EJB
    private PermissionControl permissionControl;

//    public List<PermissionDTO> getAll(){
//        return permissionControl.getAll();
//    }
}
