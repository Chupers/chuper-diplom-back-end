package com.chuper.diplom.service.impl;

import com.chuper.diplom.repository.UserFacadeRepository;
import com.chuper.diplom.service.UserFacadeService;
import org.springframework.stereotype.Service;

@Service
public class UserFacadeServiceImpl implements UserFacadeService {

    private final UserFacadeRepository userFacadeRepository;

    public UserFacadeServiceImpl(UserFacadeRepository userFacadeRepository) {
        this.userFacadeRepository = userFacadeRepository;
    }
}
