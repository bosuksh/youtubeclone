package com.goony.youtubeclone.account.repository;

import com.goony.youtubeclone.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
