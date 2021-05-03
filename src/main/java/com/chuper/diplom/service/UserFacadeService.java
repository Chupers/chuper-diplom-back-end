package com.chuper.diplom.service;

import com.chuper.diplom.entity.UserFacade;

public interface UserFacadeService {

    UserFacade findUserByName(String userName);
    UserFacade createNewUser(String userName,String password);
}
