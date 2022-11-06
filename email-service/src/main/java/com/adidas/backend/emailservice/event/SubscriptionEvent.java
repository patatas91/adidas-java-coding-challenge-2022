package com.adidas.backend.emailservice.event;

import com.adidas.backend.emailservice.dto.AdiClubMemberInfoDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SubscriptionEvent extends Event<AdiClubMemberInfoDto> {
}
