package com.goony.youtubeclone.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class AuthResponseDto {

  private String accessToken;

}
