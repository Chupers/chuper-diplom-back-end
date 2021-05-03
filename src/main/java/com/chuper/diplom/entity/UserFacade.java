package com.chuper.diplom.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class UserFacade implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @OneToOne
    private Account account;

    @NotNull
    private String password;

    @Column(unique=true)
    @NotNull
    private String userName;

    @NotNull
    private String userRole;

    private String confirmCode;

    private Boolean isAccountNotExpired;

    private Boolean isAccountNotBlocked;

    public UserFacade() {

    }

    public UserFacade(Account account,
                      String password,
                      String userName,
                      String userRole,
                      String confirmCode,
                      Boolean isAccountExpired,
                      Boolean isAccountBlocked) {
        this.account = account;
        this.password = password;
        this.userName = userName;
        this.userRole = userRole;
        this.confirmCode = confirmCode;
        this.isAccountNotExpired = isAccountExpired;
        this.isAccountNotBlocked = isAccountBlocked;
    }

    public static UserFacadeBuilder builder() {
        return new UserFacadeBuilder();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+this.userRole);
        authorityList.add(authority);
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return Objects.requireNonNullElse(this.isAccountNotExpired, true);
    }

    @Override
    public boolean isAccountNonLocked() {
       return Objects.requireNonNullElse(this.isAccountNotBlocked, true);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static class UserFacadeBuilder {
        private Account account;
        private String password;
        private String userName;
        private String userRole;
        private String confirmCode;
        private Boolean isAccountNotExpired;
        private Boolean isAccountNotBlocked;

        public UserFacadeBuilder setAccount(Account account) {
            this.account = account;
            return this;
        }

        public UserFacadeBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserFacadeBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserFacadeBuilder setUserRole(String userRole) {
            this.userRole = userRole;
            return this;
        }

        public UserFacadeBuilder setConfirmCode(String confirmCode) {
            this.confirmCode = confirmCode;
            return this;
        }

        public UserFacadeBuilder setAccountNotExpired(Boolean isAccountNotExpired) {
            this.isAccountNotExpired = isAccountNotExpired;
            return this;
        }

        public UserFacadeBuilder setAccountNotBlocked(Boolean isAccountNotBlocked) {
            this.isAccountNotBlocked = isAccountNotBlocked;
            return this;
        }

        public UserFacade build(){
            return new UserFacade(account,password,userName,userRole,confirmCode,isAccountNotExpired,isAccountNotBlocked);
        }
    }

    public Long getUserId() {
        return userId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getConfirmCode() {
        return confirmCode;
    }

    public void setConfirmCode(String confirmCode) {
        this.confirmCode = confirmCode;
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
