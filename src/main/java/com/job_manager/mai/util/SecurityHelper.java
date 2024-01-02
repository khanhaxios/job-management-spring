package com.job_manager.mai.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

public class SecurityHelper {

    public static boolean isLogged() {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }

    public static String getLoggedUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static UserDetails getUserLogged() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static void setAuthentication(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public static List<SimpleGrantedAuthority> getLoggedUserAuthorities() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .map((gr) -> new SimpleGrantedAuthority(gr.getAuthority()))
                .collect(Collectors.toList());
    }
}
