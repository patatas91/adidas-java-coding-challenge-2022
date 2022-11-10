package com.adidas.backend.adiclubservice.service.impl;

import com.adidas.backend.adiclubservice.dto.AdiClubMemberInfoDto;
import com.adidas.backend.adiclubservice.repository.MembersRepository;
import com.adidas.backend.adiclubservice.service.MembersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MembersServiceImpl implements MembersService {

    @Autowired
    private MembersRepository membersRepository;

    @Override
    public AdiClubMemberInfoDto checkMember(String email) {
        return membersRepository.findByEmail(email);
    }
}
