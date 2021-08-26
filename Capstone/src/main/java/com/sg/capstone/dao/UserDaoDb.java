package com.sg.capstone.dao;

import com.sg.capstone.models.Role;
import com.sg.capstone.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoDb implements UserDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public User getUserById(int id) {
        try {
            final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
            User user = jdbc.queryForObject(SELECT_USER_BY_ID, new UserMapper(), id);
            user.setRole(getRolesForUser(id));
            return user;
        }
        catch(DataAccessException ex){
            return null;
        }
    }

    private Role getRolesForUser(int id){
        final String SELECT_ROLE_FOR_USER = "SELECT r.* FROM role r JOIN user u ON u.roleId = r.id WHERE r.id = ?";
        return jdbc.queryForObject(SELECT_ROLE_FOR_USER, new RoleDaoDb.RoleMapper(), id);
    }

    @Override
    public User getUserByUsername(String username) {
        try{
            final String SELECT_USER_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
            User user = jdbc.queryForObject(SELECT_USER_BY_USERNAME, new UserMapper(), username);
            user.setRole(getRolesForUser(user.getId()));
            return user;
        }
        catch(DataAccessException ex){
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        final String SELECT_ALL_USERS = "SELECT * FROM user";
        List<User> allUsers = jdbc.query(SELECT_ALL_USERS, new UserMapper());
        associateRolesForUsers(allUsers);
        return allUsers;
    }

    private void associateRolesForUsers(List<User> users) {
        for (User user : users) {
            user.setRole(getRolesForUser(user.getId()));
        }
    }

    @Override
    @Transactional
    public User addUser(User user) {
        final String INSERT_USER = "INSERT INTO user(username, password, roleId) VALUES (?,?,?)";
        jdbc.update(INSERT_USER, user.getUsername(), user.getPassword(), user.getRole().getId());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        user.setId(newId);
        return user;
    }

    @Override
    public void updateUser(User user) {
        final String UPDATE_USER = "UPDATE user SET username = ?, password = ?, roleId = ? WHERE id = ?";
        jdbc.update(UPDATE_USER, user.getUsername(), user.getPassword(), user.getRole().getId(),user.getId());
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        //don t need it for now
    }

    public static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int index) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));

            return user;
        }
    }

}
