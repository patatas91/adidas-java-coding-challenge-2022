package com.adidas.backend.adiclubservice.repository;

import com.adidas.backend.adiclubservice.dto.AdiClubMemberInfoDto;

public interface MembersRepository {

    AdiClubMemberInfoDto findByEmail (String email);

}
