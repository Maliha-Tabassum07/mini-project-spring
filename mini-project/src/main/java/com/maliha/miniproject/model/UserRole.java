//package com.maliha.miniproject.model;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.util.List;
//
//
//public enum UserRole {
//    CUSTOMER,
//    ADMIN;
//
//    public List<GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority("ROLE_" + this.name()));
//    }
//}
