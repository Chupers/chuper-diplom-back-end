package com.chuper.diplom.service.impl;

import com.chuper.diplom.repository.AccountRepository;
import com.chuper.diplom.service.AccountService;
import com.chuper.diplom.service.UserFacadeService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserFacadeService userFacadeService;

    public AccountServiceImpl(AccountRepository accountRepository, UserFacadeService userFacadeService) {
        this.accountRepository = accountRepository;
        this.userFacadeService = userFacadeService;
    }
}
