/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstone.dao;

import com.sg.capstone.models.Posts;
import com.sg.capstone.models.Role;
import com.sg.capstone.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Raluca
 */
@Component
public class CapstoneDbDao implements CapstoneDao{
    @Autowired
    JdbcTemplate jdbc;
    
    @Autowired
    JdbcTemplate template;

    @Override
    public User getUserById(int id) throws InvalidIdException {

        String select = "SELECT id, username, password, enabled FROM user WHERE id = ?";

        User toReturn = null;
        try {
            toReturn = jdbc.queryForObject(select, new UserMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
        }

        if (toReturn == null) {
            throw new InvalidIdException("Invalid User Id.");
        }

        Set<Role> userRoles = getRolesForUser(id);
        toReturn.setRoles(userRoles);

        return toReturn;

    }

    @Override
    public User getUserByUsername(String username) {

        String select = "SELECT id, username, password, enabled FROM user WHERE username = ?";

        User toReturn = jdbc.queryForObject(select, new UserMapper(), username);

        Set<Role> userRoles = getRolesForUser(toReturn.getId());

        toReturn.setRoles(userRoles);

        return toReturn;

    }

    @Override
    public List<User> getAllUsers() {

        String select = "SELECT id, username, password, enabled FROM user";

        List<User> allUsers = jdbc.query(select, new UserMapper());

        for (User toFinish : allUsers) {

            Set<Role> userRoles = this.getRolesForUser(toFinish.getId());
            toFinish.setRoles(userRoles);

        }

        return allUsers;

    }

    @Override
    public void updateUser(User user) {

        String updateStatement = "UPDATE user SET username = ?, password = ?, enabled = ? WHERE Id = ?";

        int updateRowsAffected = jdbc.update(updateStatement, user.getUsername(), user.getPassword(), user.isEnabled(), user.getId());

        //Check that rows affected = 1
        String deleteStatement = "DELETE FROM user_role WHERE user_id = ?";

        int deleteRowsAffected = jdbc.update(deleteStatement, user.getId());

        String insert = "INSERT INTO user_role (user_id, role_id) VALUES (?,?)";

        for (Role toAdd : user.getRoles()) {

            int insertRowsAffected = jdbc.update(insert, user.getId(), toAdd.getId());

            //TODO: Make sure that one row was affected; otherwise house is on fire
        }

    }

    @Override
    public void deleteUser(int id) {

        String roleDelete = "DELETE FROM user_role WHERE user_id = ?";

        int roleRowsAffected = jdbc.update(roleDelete, id);

        String userDelete = "DELETE FROM user WHERE id = ?";

        int deleteRowsAffected = jdbc.update(userDelete, id);

    }

    @Transactional
    @Override
    public User createUser(User user) {

        String insertStatement = "INSERT INTO User (username, password, enabled) VALUES (?,?,?)";

        int insertRows = jdbc.update(insertStatement, user.getUsername(), user.getPassword(), user.isEnabled());

        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);

        user.setId(newId);

        String roleInsert = "INSERT INTO user_role (user_id, role_id) VALUES (?,?);";
        for (Role toAdd : user.getRoles()) {

            int roleRowsAffected = jdbc.update(roleInsert, newId, toAdd.getId());
 
        }

        return user;

    }

    private Set<Role> getRolesForUser(int id) {

        String selectStatement = "SELECT id, `role`\n"
                + "FROM `role`\n"
                + "INNER JOIN user_role ON id = role_id\n"
                + "WHERE user_id =?";

        Set<Role> roles = new HashSet<Role>(jdbc.query(selectStatement, new RoleMapper(), id));

        return roles;

    }

    @Override
    public Role getRoleById(int id) {

        String select = "SELECT id, role FROM role WHERE id = ?";

        Role toReturn = jdbc.queryForObject(select, new RoleMapper(), id);

        return toReturn;

    }

    @Override
    public Role getRoleByRole(String role) {

        String select = "SELECT id, role FROM role WHERE role = ?";

        Role toReturn = jdbc.queryForObject(select, new RoleMapper(), role);

        return toReturn;

    }

    @Override
    public List<Role> getAllRoles() {
        String select = "SELECT id, role  FROM role";

        List<Role> allRoles = jdbc.query(select, new RoleMapper());

        return allRoles;
    }

    @Override
    public void deleteRole(int id) {

        String userDelete = "DELETE FROM user_role WHERE role_id = ?";

        int userRowsAffected = jdbc.update(userDelete, id);

        String roleDelete = "DELETE FROM role WHERE id = ?";

        int deleteRowsAffected = jdbc.update(roleDelete, id);
    }

    @Override
    public void updateRole(Role role) {

        String updateStatement = "UPDATE role SET role = ? WHERE Id = ?";

        int updateRowsAffected = jdbc.update(updateStatement, role.getRole(), role.getId());

        //Check that rows affected = 1
    }

    @Override
    @Transactional
    public Role createRole(Role role) {

        String insertStatement = "INSERT INTO Role (role) VALUES (?)";

        int insertRows = jdbc.update(insertStatement, role.getRole());

        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);

        role.setId(newId);

        return role;

    }

   
    public static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet results, int rowNumber) throws SQLException {
            User user = new User();
            user.setId(results.getInt("id"));
            user.setUsername(results.getString("username"));
            user.setPassword(results.getString("password"));
            user.setEnabled(results.getBoolean("enabled"));
            return user;
        }
    }

    public static final class RoleMapper implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet results, int rowNumber) throws SQLException {

            Role toReturn = new Role();

            toReturn.setId(results.getInt("id"));
            toReturn.setRole(results.getString("role"));

            return toReturn;

        }

    }
    
    @Override
    public List<Posts> getAllPosts() {

        String query = "Select posts.id commentid, posts.userid, posts.post, user.username, user.`password`, user.enabled\n"
                + "From posts \n"
                + "Inner Join `User` On posts.userid = user.id\n";

        List<Posts> toReturn = template.query(query, new PostMapper());

        return toReturn;
    }

    @Override
    public Posts getPostById(int id) throws InvalidIdException {

        String query = "Select posts.postId, posts.userId, posts.post, user.username, user.`password`, user.enabled\n"
                + "From posts \n"
                + "Inner Join `User` On posts.userId = user.id\n"
                + "Where posts.postId = ?";

        Posts toReturn = null;
        try {
            toReturn = template.queryForObject(query, new PostMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
        }

        if (toReturn == null) {
            throw new InvalidIdException("Invalid Post Id.");
        }

        return toReturn;
    }
    
     @Override
    public Posts addPost(Posts toAdd) throws CapstonePersistenceException, InvalidIdException {

        if (toAdd == null || toAdd.getUser() == null) {
            throw new CapstonePersistenceException("Post to add is null.");
        }

        try {
            User userToCheck = getUserById(toAdd.getUser().getId());
        } catch (InvalidIdException ex) {
            throw new InvalidIdException("UserId not found", ex);
        }

        String insert = "Insert into posts(userid, roleId, post)\n"
                + "Values\n"
                + "(?, ?, ?)";

        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement toReturn = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

                toReturn.setInt(1, toAdd.getUser().getId());
                toReturn.setString(2, toAdd.getCommentText());

                return toReturn;
            }
        };

        template.update(psc, holder);
        int newBeachCommentId = holder.getKey().intValue();

        toAdd.setId(newBeachCommentId);

        return toAdd;
    }
    
    @Override
    public void updatePost(Posts updatedPost) throws CapstonePersistenceException, InvalidIdException {

        if (updatedPost == null || updatedPost.getUser() == null) {
            throw new CapstonePersistenceException("Post is null.");
        }

        try {
            User userToCheck = getUserById(updatedPost.getUser().getId());
        } catch (InvalidIdException ex) {
            throw new InvalidIdException("UserId not found", ex);
        }

        String updateStatement = "Update post \n"
                + "Set userId = ?,\n"
                + "post = ?\n"
                + "Where postId = ?";

        int rowsAffected = template.update(updateStatement,
                updatedPost.getUser().getId(),
                updatedPost.getCommentText(),
                updatedPost.getId());

        if (rowsAffected == 0) {
            throw new InvalidIdException("Could not edit Post with id = " + updatedPost.getId());
        }

        if (rowsAffected > 1) {
            throw new InvalidIdException("ERROR: Post Id IS NOT UNIQUE FOR Post TABLE.");
        }
    }
    
     @Override
    public void deletePost(int id) throws InvalidIdException {

        try {
            Posts toCheck = getPostById(id);
        } catch (InvalidIdException ex) {
            throw new InvalidIdException("Invalid Post Id requested.");
        }

        String deleteStatement = "Delete \n"
                + "From posts \n"
                + "Where postId = ?";

        template.update(deleteStatement, id);
    }
    
    private class PostMapper implements RowMapper<Posts> {

        @Override
        public Posts mapRow(ResultSet results, int rowNum) throws SQLException {
            Posts toReturn = new Posts();
            toReturn.setId(results.getInt("CommentId"));
            toReturn.setCommentText(results.getString("Comment"));

            User user = new User();
            user.setId(results.getInt("Userid"));
            user.setUsername(results.getString("Username"));
            user.setPassword(results.getString("Password"));
            user.setEnabled(results.getBoolean("Enabled"));
            toReturn.setUser(user);

            return toReturn;
        }
    }

}
