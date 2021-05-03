package com.chuper.diplom.security;

import com.chuper.diplom.service.UserFacadeService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private final UserFacadeService userFacadeService;

    public UserPrincipalDetailsService(UserFacadeService userFacadeService) {
        this.userFacadeService = userFacadeService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userFacadeService.findUserByName(userName);
    }
}
