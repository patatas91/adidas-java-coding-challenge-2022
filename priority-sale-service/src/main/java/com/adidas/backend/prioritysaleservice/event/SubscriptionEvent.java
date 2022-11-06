package com.adidas.backend.prioritysaleservice.event;

import com.adidas.backend.prioritysaleservice.dto.AdiClubMemberInfoDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SubscriptionEvent extends Event<AdiClubMemberInfoDto> {
}
