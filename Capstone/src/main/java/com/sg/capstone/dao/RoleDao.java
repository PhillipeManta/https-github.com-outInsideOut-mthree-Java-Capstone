package com.sg.capstone.dao;

import com.sg.capstone.models.Role;

import java.util.List;

public interface RoleDao {

    Role getRoleById(int id);
    List<Role> getAllRoles();
    Role addRole(Role role);
    void updateRole(Role role);
    void deleteRoleById(int id);

}
