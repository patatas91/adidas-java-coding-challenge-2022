package com.adidas.backend.emailservice.service;

import com.adidas.backend.emailservice.dto.AdiClubMemberInfoDto;
import com.adidas.backend.emailservice.event.Event;

public interface SubscriptionEventsService {
    //void consumer (Event<?> event);
    AdiClubMemberInfoDto getWinner();
}
