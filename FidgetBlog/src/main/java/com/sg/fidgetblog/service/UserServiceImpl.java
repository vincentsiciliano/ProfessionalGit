/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.service;

import com.sg.fidgetblog.dao.UserDao;
import com.sg.fidgetblog.dto.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author jono
 */
public class UserServiceImpl implements UserService {

    UserDao userDao;

    @Inject
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Override
    public User readUserById(int userId) {
        return userDao.readUserById(userId);
    }

    @Override
    public User readUserByUsername(String userName) {
        return userDao.readUserByUserName(userName);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(String userName) {
        userDao.deleteUserByUserName(userName);
    }

    @Override
    public List<User> readAllUsers(int numChoice) {

        List<User> userList = userDao.readAllUsers(numChoice);

        for (User i : userList) {
            if (!i.getIsActive()) {
                i.setColorStatus("grey");
            }

            if (i.getIsBanned()) {
                i.setColorStatus("red");
            }

            if (i.getIsActive()) {
                i.setColorStatus("green");
            }
        }

        return userList;
    }

    @Override
    public void deleteUserAuthority(String userName, String authority) {
        userDao.deleteUserAuthority(userName, authority);
    }

    @Override
    public void restoreUser(int userId) {

    }

    @Override
    public void deleteAllAuthoritiesByUserName(String userName) {
        userDao.deleteAllAuthoritiesByUserName(userName);
    }

    @Override
    public List<User> actuallyReadAllUsers() {
        return userDao.actuallyReadAllUsers();
    }

    @Override
    public List<Integer> getNumCommentsForUserList(List<User> users) {
        List<Integer> commentNums = new ArrayList();
        for (User user : users) {
            commentNums.add(userDao.getNumCommentsByUserId(user.getUserId()));
        }
        return commentNums;
    }

    @Override
    public Map<User, Integer> createUserCommentMap(List<User> userList, List<Integer> commentList) {
        Map<User, Integer> userCommentMap = new HashMap();

        for (int i = 0; i < userList.size(); i++) {
            userCommentMap.put(userList.get(i), commentList.get(i));
        }
        return userCommentMap;
    }

}
