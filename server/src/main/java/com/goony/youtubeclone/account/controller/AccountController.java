package com.goony.youtubeclone.account.controller;

import com.goony.youtubeclone.account.domain.Account;
import com.goony.youtubeclone.account.domain.AccountRole;
import com.goony.youtubeclone.account.dto.AccountRequestDto;
import com.goony.youtubeclone.account.dto.AccountResponseDto;
import com.goony.youtubeclone.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilderDsl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/account", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @PostMapping
  public ResponseEntity<?> createAccount(@RequestBody AccountRequestDto requestDto, Errors errors) {

    if(errors.hasErrors()) {
      return ResponseEntity.badRequest().build();
    }
    Account requestedAccount = Account.builder()
                           .email(requestDto.getEmail())
                           .password(requestDto.getPassword())
                           .name(requestDto.getName())
                           .accountRole(AccountRole.USER)
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
