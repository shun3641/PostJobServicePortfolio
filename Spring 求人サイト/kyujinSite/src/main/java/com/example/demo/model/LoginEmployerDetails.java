package com.example.demo.model;

import java.util.Arrays;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class LoginEmployerDetails implements UserDetails {

  private static final long serialVersionUID = 4154080474752539938L;
  private final String email;
  private final String password;
  private final String name;
  private final Collection <? extends GrantedAuthority> authorities;
  
  public LoginEmployerDetails(Employer employer) {
    this.email = employer.getEmail();
    this.password = employer.getPassword();
    this.name = employer.getName();
    this.authorities = Arrays.stream(employer.getRoles().split(","))
        .map(role -> new SimpleGrantedAuthority(role))
        .toList();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // ロールのコレクションを返す
    return authorities;
  }

  @Override
  public String getPassword() {
    // パスワードを返す
    return password;
  }

  @Override
  public String getUsername() {
    // ログイン名を返す
    return email;
  }

  public String getName() {
    // ユーザー名を返す
    return name;
  }
  
  @Override
  public boolean isAccountNonExpired() {
    //  ユーザーが期限切れでなければtrueを返す
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    //  ユーザーがロックされていなければtrueを返す
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    //  パスワードが期限切れでなければtrueを返す
    return true;
  }

  @Override
  public boolean isEnabled() {
    //  ユーザーが有効ならtrueを返す
    return true;
  }
}