package com.chuper.diplom.service.impl;

import com.chuper.diplom.entity.Account;
import com.chuper.diplom.entity.UserFacade;
import com.chuper.diplom.repository.AccountRepository;
import com.chuper.diplom.service.AccountService;
import com.chuper.diplom.service.UserFacadeService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserFacadeService userFacadeService;

    public AccountServiceImpl(AccountRepository accountRepository, UserFacadeService userFacadeService) {
        this.accountRepository = accountRepository;
        this.userFacadeService = userFacadeService;
    }

    @Override
    public Account registrationAccount(String login,String password){
        UserFacade userFacade =  userFacadeService.createNewUser(login, password);
        Account account = new Account();
        account.setHost(false);
        account.setUserFacade(userFacade);
        return accountRepository.save(account);
    }

    @Override
    public Account getActiveAccount() {
        return accountRepository.findByUserFacadeUserName(getUserNameFromSecurityContext());
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    private String getUserNameFromSecurityContext(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
