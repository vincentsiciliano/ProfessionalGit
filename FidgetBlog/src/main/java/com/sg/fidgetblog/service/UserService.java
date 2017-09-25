/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.service;

import com.sg.fidgetblog.dto.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jono
 */
public interface UserService {

    public void createUser(User user);

    public User readUserById(int userId);

    public User readUserByUsername(String userName);

    public List<User> readAllUsers(int selectNum);

    public List<User> actuallyReadAllUsers();

    public void updateUser(User user);

    public void deleteUser(String userName);

    public void restoreUser(int userId);

    public void deleteUserAuthority(String userName, String authority);

    public void deleteAllAuthoritiesByUserName(String userName);

    public List<Integer> getNumCommentsForUserList(List<User> users);

    public Map<User, Integer> createUserCommentMap(List<User> userList, List<Integer> commentList);
}
