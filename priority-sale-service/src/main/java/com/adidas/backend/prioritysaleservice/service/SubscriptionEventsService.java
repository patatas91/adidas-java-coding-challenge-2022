package com.adidas.backend.prioritysaleservice.service;

import com.adidas.backend.prioritysaleservice.dto.AdiClubMemberInfoDto;

public interface SubscriptionEventsService {
    void publish (AdiClubMemberInfoDto member);
}
