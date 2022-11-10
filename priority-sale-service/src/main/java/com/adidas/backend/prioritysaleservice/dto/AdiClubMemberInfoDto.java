package com.adidas.backend.prioritysaleservice.dto;

import lombok.*;

import java.time.Instant;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdiClubMemberInfoDto {
  private String email;
  private Integer points;
  private String registrationDate;
}
