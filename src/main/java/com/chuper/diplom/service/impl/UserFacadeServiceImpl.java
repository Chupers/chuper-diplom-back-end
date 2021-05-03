package com.chuper.diplom.service.impl;

import com.chuper.diplom.entity.Account;
import com.chuper.diplom.entity.UserFacade;
import com.chuper.diplom.repository.UserFacadeRepository;
import com.chuper.diplom.security.UserRoleList;
import com.chuper.diplom.service.UserFacadeService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserFacadeServiceImpl implements UserFacadeService {

    private final UserFacadeRepository userFacadeRepository;

    public UserFacadeServiceImpl(UserFacadeRepository userFacadeRepository) {
        this.userFacadeRepository = userFacadeRepository;
    }

    @Override
    public UserFacade findUserByName(String userName) {
        return userFacadeRepository.findByUserName(userName);
    }

    @Override
    public UserFacade createNewUser(String userName, String password) {
        UserFacade userFacade = UserFacade.builder()
                .setAccountNotBlocked(true)
                .setAccountNotExpired(true)
                .setUserName(userName)
                .setUserRole(UserRoleList.USER)
                .setPassword(new BCryptPasswordEncoder().encode(password))
                .setConfirmCode(new BCryptPasswordEncoder().encode(userName)).build();

        return userFacadeRepository.save(userFacade);
    }
}
