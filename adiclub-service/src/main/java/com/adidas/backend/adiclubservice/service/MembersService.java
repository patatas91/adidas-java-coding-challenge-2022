package com.adidas.backend.adiclubservice.service;

import com.adidas.backend.adiclubservice.dto.AdiClubMemberInfoDto;

public interface MembersService {
    AdiClubMemberInfoDto checkMember (String email);
}
