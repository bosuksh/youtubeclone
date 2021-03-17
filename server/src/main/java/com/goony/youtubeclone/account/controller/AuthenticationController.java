package com.goony.youtubeclone.account.controller;

import com.goony.youtubeclone.account.domain.AccountRole;
import com.goony.youtubeclone.account.dto.AuthRequestDto;
import com.goony.youtubeclone.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationManager authenticationManager;

  @PostMapping
  public ResponseEntity<?> signIn(@RequestBody AuthRequestDto requestDto) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword()));
    return ResponseEntity.ok(jwtTokenProvider.createToken(requestDto.getEmail(), Set.of(AccountRole.USER)));
  }


}
