package com.adidas.backend.adiclubservice.repository.impl;

import com.adidas.backend.adiclubservice.dto.AdiClubMemberInfoDto;
import com.adidas.backend.adiclubservice.exception.MembersNotFoundException;
import com.adidas.backend.adiclubservice.repository.MembersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Slf4j
@Repository
public class MembersRepositoryImpl implements MembersRepository {

    private static final Random RANDOM = new Random(System.nanoTime());

    /**
     * To simulate non members users Im generating members if the number of points is odd
     * @param email
     * @return
     */
    @Override
    public AdiClubMemberInfoDto findByEmail(String email) {
        int points = RANDOM.nextInt(5000);

        if (points % 2 == 0) {
            log.info("Found!");
            return AdiClubMemberInfoDto
                    .builder()
                    .email(email)
                    .points(points)
                    .registrationDate(Instant.now().minus(RANDOM.nextInt(365), ChronoUnit.DAYS))
                    .build();

        } else {
            log.info("Not found!");
            throw new MembersNotFoundException();
        }
    }

}
