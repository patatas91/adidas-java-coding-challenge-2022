package com.adidas.backend.emailservice.service.impl;

import com.adidas.backend.emailservice.dto.AdiClubMemberInfoDto;
import com.adidas.backend.emailservice.service.SubscriptionEventsService;
import com.adidas.backend.emailservice.service.WinnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WinnerServiceImpl implements WinnerService {

    @Autowired
    SubscriptionEventsService subscriptionEventsService;

    @Override
    public AdiClubMemberInfoDto getWinner() {
        return subscriptionEventsService.getWinner();
    }
}
