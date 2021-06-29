package com.camper.camport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CamportSearchCondition {
    private Long memberId;
    private Long camportId;

    public CamportSearchCondition(Long memberId) {
        this.memberId = memberId;
    }

    public CamportSearchCondition(Long memberId, Long camportId) {
        this.memberId = memberId;
        this.camportId = camportId;
    }
}
