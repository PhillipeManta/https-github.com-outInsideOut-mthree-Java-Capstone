/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstone.dao;

import com.sg.capstone.models.Role;
import java.util.List;

/**
 *
 * @author Raluca
 */
public interface RoleDao {

    Role getRoleById(int id);
    List<Role> getAllRoles();
    Role addRole(Role role);
    void updateRole(Role role);
    void deleteRoleById(int id);

}