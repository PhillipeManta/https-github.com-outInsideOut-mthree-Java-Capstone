package com.sg.capstone.dao;

import com.sg.capstone.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RoleDaoDb implements RoleDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Role getRoleById(int id) {
        try {
            final String GET_ROLE_BY_ID = "SELECT * FROM role WHERE id = ?";
            return jdbc.queryForObject(GET_ROLE_BY_ID, new RoleMapper(), id);
        }
        catch(DataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Role> getAllRoles() {
        final String GET_ALL_ROLES = "SELECT * FROM role";
        return jdbc.query(GET_ALL_ROLES, new RoleMapper());
    }

    @Override
    @Transactional
    public Role addRole(Role role) {
        final String INSERT_ROLE = "INSERT INTO role (role) VALUES (?)";
        jdbc.update(INSERT_ROLE, role.getRole());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        role.setId(newId);
        return role;
    }

    @Override
    public void updateRole(Role role) {
        final String UPDATE_ROLE = "UPDATE role SET role = ? WHERE id = ? ";
        jdbc.update(UPDATE_ROLE, role.getRole(), role.getId());
    }

    @Override
    @Transactional
    public void deleteRoleById(int id) {

        String userDelete = "DELETE FROM user WHERE roleId = ?";
        jdbc.update(userDelete, id);

        String roleDelete = "DELETE FROM role WHERE id = ?";
        jdbc.update(roleDelete, id);

    }

    public static final class RoleMapper implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet rs, int index) throws SQLException {
            Role role = new Role();
            role.setId(rs.getInt("id"));
            role.setRole(rs.getString("role"));

            return role;
        }
    }
}
