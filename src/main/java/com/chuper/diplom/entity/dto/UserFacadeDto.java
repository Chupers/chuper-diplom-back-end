package com.chuper.diplom.entity.dto;

import com.chuper.diplom.entity.Account;
import com.sun.istack.NotNull;

import javax.persistence.Column;

public class UserFacadeDto {
    private String userName;
    private Boolean isAccountNotExpired;
    private Boolean isAccountNotBlocked;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getAccountNotExpired() {
        return isAccountNotExpired;
    }

    public void setAccountNotExpired(Boolean accountNotExpired) {
        isAccountNotExpired = accountNotExpired;
    }

    public Boolean getAccountNotBlocked() {
        return isAccountNotBlocked;
    }

    public void setAccountNotBlocked(Boolean accountNotBlocked) {
        isAccountNotBlocked = accountNotBlocked;
    }
}
