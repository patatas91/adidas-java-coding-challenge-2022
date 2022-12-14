package com.adidas.backend.publicservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdiClubMemberInfoDto {
  private String email;
  private Integer points;
  private Instant registrationDate;
}
