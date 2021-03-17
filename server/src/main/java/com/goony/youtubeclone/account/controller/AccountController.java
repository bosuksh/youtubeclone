package com.goony.youtubeclone.account.controller;

import com.goony.youtubeclone.account.domain.Account;
import com.goony.youtubeclone.account.domain.AccountRole;
import com.goony.youtubeclone.account.dto.AccountRequestDto;
import com.goony.youtubeclone.account.dto.AccountResponseDto;
import com.goony.youtubeclone.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/signup", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class AccountController {

  private final AccountService accountService;

  @PostMapping
  public ResponseEntity<?> createAccount(@RequestBody @Valid AccountRequestDto requestDto) {
    Account requestedAccount = Account.builder()
                           .email(requestDto.getEmail())
                           .password(requestDto.getPassword())
                           .name(requestDto.getName())
                           .accountRoles(Set.of(AccountRole.USER))
                           .build();
    Account newAccount = accountService.createAccount(requestedAccount);
    URI uri = linkTo(AccountController.class).slash(newAccount.getId()).toUri();

    AccountResponseDto responseDto = AccountResponseDto.builder()
                                       .id(newAccount.getId())
                                       .email(newAccount.getEmail())
                                       .name(newAccount.getName())
                                       .build();
    return ResponseEntity.created(uri).body(responseDto);
  }

}
