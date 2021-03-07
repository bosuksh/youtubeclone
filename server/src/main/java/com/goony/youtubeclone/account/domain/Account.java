package com.goony.youtubeclone.account.domain;

import com.goony.youtubeclone.common.domain.BaseEntity;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity @AllArgsConstructor @NoArgsConstructor
@Getter @Builder
public class Account extends BaseEntity{

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NotBlank
  private Long id;

  @Column(unique = true)
  @NotBlank @Email
  private String email;

  @NotNull
  private String password;

  private String name;

}
