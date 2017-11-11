package com.ubold.admin.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by lenovo on 2017/11/11.
 */
public class UserSessionContext implements UserDetails{
    Collection<? extends GrantedAuthority> getAuthorities;
    String password;
    String username;
    boolean isAccountNonExpired;
    boolean isAccountNonLocked;
    boolean isCredentialsNonExpired;
    boolean isEnabled;

    public UserSessionContext(String username, String password,
                              boolean isAccountNonExpired,
                              boolean isAccountNonLocked,
                              boolean isCredentialsNonExpired,
                              boolean isEnabled,
                              List<GrantedAuthority> getAuthorities){
        this.username = username;
        this.password = password;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
