package com.camper.camport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CamportDto {
    private Long camportId;
    private Long memberId;
    private Long campLocationId;
    private String campLocationName;
    private String campLocationAddress;
}
