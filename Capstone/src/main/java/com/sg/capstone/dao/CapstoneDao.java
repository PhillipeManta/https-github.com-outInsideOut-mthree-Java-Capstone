package com.sg.capstone.dao;

import com.sg.capstone.models.Posts;
import com.sg.capstone.models.Role;
import com.sg.capstone.models.User;
import java.util.List;

/**
 *
 * @author kylerudy
 */
public interface CapstoneDao {
    User getUserById(int id) throws InvalidIdException;
    User getUserByUsername(String username);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int id);
    User createUser(User user);
    
    Role getRoleById(int id);
    Role getRoleByRole(String role);
    List<Role> getAllRoles();
    void deleteRole(int id);
    void updateRole(Role role);
    Role createRole(Role role);
    
    List<Posts> getAllPosts();
    Posts getPostById(int id) throws InvalidIdException;
    Posts addPost(Posts toAdd) throws CapstonePersistenceException, InvalidIdException;
    void updatePost(Posts updatedPost) throws CapstonePersistenceException, InvalidIdException;
    void deletePost(int id) throws InvalidIdException;
}