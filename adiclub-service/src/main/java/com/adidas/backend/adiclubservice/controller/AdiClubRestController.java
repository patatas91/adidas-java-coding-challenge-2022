package com.adidas.backend.adiclubservice.controller;

import com.adidas.backend.adiclubservice.service.MembersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.backend.adiclubservice.dto.AdiClubMemberInfoDto;

@Slf4j
@RestController
@RequestMapping(value = "/adiclub")
public class AdiClubRestController {

  @Autowired
  private MembersService membersService;

  @GetMapping
  public ResponseEntity<AdiClubMemberInfoDto> getAdiClubMemberInfo(
      @RequestParam(value = "emailAddress") final String emailAddress) {
    log.info("ADI-CLUB service /adiclub - Requested member data for {}", emailAddress);

    AdiClubMemberInfoDto member = membersService.checkMember(emailAddress);

    return ResponseEntity
            .ok()
            .body(member);
  }
}
