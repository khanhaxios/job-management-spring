package com.job_manager.mai.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Table(name = "accounts")
@Entity
@Data
public class Account implements UserDetails {
    public Account() {
        this.Id = UUID.randomUUID().toString();
    }

    @Id
    private String Id;
    private String username;
    private String password;

    @Column(name = "is_verify")
    private boolean isVerify;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @Column(name = "last_ip_login")
    private String lastIpLogin;

    @ManyToOne
    private Role role;
    @OneToOne
    private User user;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getRoleName()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isActive;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive && this.isVerify;
    }
}
