package msg.permission.control;

import msg.permission.entity.PermissionDAO;
import msg.permission.entity.dto.PermissionDTO;

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
public class PermissionControl {



    @EJB
    private PermissionDAO permissionDao;

//    public List<PermissionDTO> getAll(){
//        return permissionDao.getAll()
//                .stream()
//                .map()
//    }

}
