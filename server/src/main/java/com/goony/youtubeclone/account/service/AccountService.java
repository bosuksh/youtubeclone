package com.goony.youtubeclone.account.service;

import com.goony.youtubeclone.account.domain.Account;
import com.goony.youtubeclone.account.domain.AccountRole;
import com.goony.youtubeclone.account.repository.AccountRepository;
import com.goony.youtubeclone.common.error.exception.UserDuplicatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

  private final AccountRepository accountRepository;

  public Account createAccount(Account account) {
    accountRepository.findByEmail(account.getEmail()).ifPresent(a -> {throw new UserDuplicatedException();});
    return accountRepository.save(account);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Account account = accountRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));

    return new User(account.getEmail(), account.getPassword(), getAuthorities(account.getAccountRoles()));
  }

  private Collection<? extends GrantedAuthority> getAuthorities(Set<AccountRole> accountRoles) {
    return accountRoles.stream()
      .map(r -> new SimpleGrantedAuthority("ROLE_"+r))
      .collect(Collectors.toList());
  }


}
