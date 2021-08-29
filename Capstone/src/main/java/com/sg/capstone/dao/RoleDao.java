package com.sg.capstone.dao;

import com.sg.capstone.models.Role;

import java.util.List;

/**
 * Interface for the Role DTO
 */
public interface RoleDao {

    Role getRoleById(int id);
    List<Role> getAllRoles();
    Role addRole(Role role);
    void updateRole(Role role);
    void deleteRoleById(int id);

}
