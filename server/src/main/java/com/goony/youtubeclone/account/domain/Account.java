package com.goony.youtubeclone.account.domain;

import com.goony.youtubeclone.common.domain.BaseEntity;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity @AllArgsConstructor @NoArgsConstructor
@Getter @Builder @EqualsAndHashCode(of = "id")
public class Account extends BaseEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  @NotNull @NotBlank @Email
  private String email;

  @NotNull
  private String password;

  private String name;

  private String token;

  @ElementCollection(fetch = FetchType.EAGER)
  @Enumerated(value = EnumType.STRING)
  private Set<AccountRole> accountRoles = new HashSet<>();

}
