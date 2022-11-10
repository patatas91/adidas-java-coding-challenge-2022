package com.adidas.backend.emailservice.controller;

import com.adidas.backend.emailservice.dto.AdiClubMemberInfoDto;
import com.adidas.backend.emailservice.service.WinnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/winner")
public class WinnerRestController {

  @Autowired
  private WinnerService winnerService;

  @GetMapping
  public ResponseEntity<AdiClubMemberInfoDto> winner() {
    log.info("EMAIL service /winner - Requested winner member");

    AdiClubMemberInfoDto result = winnerService.getWinner();

    return ResponseEntity
            .ok()
            .body(result);
  }
}
