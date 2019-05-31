package msg.role.boundary;

import msg.role.control.RoleControl;
import msg.role.entity.RoleEntity;
import msg.role.entity.dto.RoleDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Document me.
 *
 * @author msg systems AG; Diana.
 * @since 19.1.2
 */
@Stateless
public class RoleFacade {

    @EJB
    private RoleControl roleControl;

    public void updateRole(RoleDTO roleDTO){
        this.roleControl.updateRole(roleDTO);
    }

    public List<RoleDTO> getAll(){
        return roleControl.getAll();
    }

    public RoleEntity getRoleByType(String type){
       return roleControl.getRoleByType(type);
    }
}
