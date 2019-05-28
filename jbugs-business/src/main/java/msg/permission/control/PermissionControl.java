package msg.permission.control;

import msg.permission.entity.PermissionDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;

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


}
