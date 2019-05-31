package msg.permission.control;

import msg.permission.entity.PermissionDAO;
import msg.permission.entity.PermissionEntity;
import msg.permission.entity.dto.PermissionConverter;
import msg.permission.entity.dto.PermissionDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    @EJB
    private PermissionConverter permissionConverter;

    public List<PermissionDTO> getAll(){
        return permissionDao.getAll()
                .stream()
                .map(permissionConverter::convertEntityToDTO)
                .collect(Collectors.toList());
    }


    public PermissionEntity getPermissionByType(String type) {
        return permissionDao.getPermissionByType(type);
    }
}
