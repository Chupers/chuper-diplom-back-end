package com.chuper.diplom.service;

import com.chuper.diplom.entity.Account;

public interface AccountService {
    Account registrationAccount(String login,String password);
    Account getActiveAccount();
    Account saveAccount(Account account);
}
