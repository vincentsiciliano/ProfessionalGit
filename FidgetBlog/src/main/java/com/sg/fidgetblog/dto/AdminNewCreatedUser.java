
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dto;

import java.util.ArrayList;
import java.util.Objects;

//ROLE_ADMIN
//ROLE_USER
//ROLE_CONTENTMANAGER
/**
 *
 * @author vincentsiciliano
 */
public class AdminNewCreatedUser {

    
    private String userName;
    private String passWord;
    private String authority;

    public AdminNewCreatedUser() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}