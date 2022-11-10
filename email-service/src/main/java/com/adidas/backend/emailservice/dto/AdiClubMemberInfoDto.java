package com.adidas.backend.emailservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.Instant;

@Data
@ToString
@Builder
public class AdiClubMemberInfoDto {
  private String email;
  private Integer points;
  private String registrationDate;

  @JsonIgnore
  public Instant getInstant() {
    return Instant.parse(this.getRegistrationDate());
  }
}
