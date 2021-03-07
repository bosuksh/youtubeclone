package com.goony.youtubeclone.account.service;

import com.goony.youtubeclone.account.domain.Account;
import com.goony.youtubeclone.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository accountRepository;

  public Account createAccount(Account account) {
    return accountRepository.save(account);
  }
}
