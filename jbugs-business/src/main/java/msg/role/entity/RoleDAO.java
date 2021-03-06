// =================================================================================================
// Copyright (c) 2017-2020 BMW Group. All rights reserved.
// =================================================================================================
package msg.role.entity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * The DAO for the RoleEntity Entities.
 *
 * @author msg-system ag;  Daniel Donea
 * @since 1.0
 */
@Stateless
public class RoleDAO {

    @PersistenceContext(unitName="jbugs-persistence")
    private EntityManager em;

    /**
     * Given a input list of {@link RoleEntity#getType()}s, returns the corresponding list of RoleEntity Entities.
     *
     * @param typeList a list of role types.
     * @return a list of role entities.
     */
    public List<RoleEntity> getRolesByTypeList(final List<String> typeList) {
        return em.createNamedQuery(RoleEntity.QUERY_GET_ROLES_BY_TYPE_LIST, RoleEntity.class)
                .setParameter(RoleEntity.INPUT_TYPE_LIST, typeList)
                .getResultList();
    }

    public RoleEntity getRoleByType(String type) {
        RoleEntity roleEntity = null;
        try{
            roleEntity = em.createNamedQuery(RoleEntity.QUERY_GET_ROLE_BY_TYPE, RoleEntity.class)
                    .setParameter(RoleEntity.INPUT_TYPE_LIST, type).getSingleResult();


        }catch (Exception e){
            e.printStackTrace();
        }
        return roleEntity;
    }

    public RoleEntity updateRole(RoleEntity roleEntity){
        em.merge(roleEntity);
        return roleEntity;
    }

    public List<RoleEntity> getAll(){
        return em.createNamedQuery(RoleEntity.ROLE_FIND_ALL,RoleEntity.class).getResultList();

    }
}
