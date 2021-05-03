package com.chuper.diplom.controller;

import com.chuper.diplom.entity.Account;
import com.chuper.diplom.entity.dto.AccountDto;
import com.chuper.diplom.service.AccountService;
import org.dozer.DozerBeanMapper;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 10000)
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final DozerBeanMapper mapper;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
        this.mapper = new DozerBeanMapper();
    }

    @PostMapping("/create")
    public Account createUser(@RequestParam(name = "name") String userName, @RequestParam(name = "password") String password){
         return accountService.registrationAccount(userName,password);
    }

    @GetMapping("/active")
    public AccountDto getActiveAccount(){
        return mapper.map(accountService.getActiveAccount(),AccountDto.class);
    }
}
