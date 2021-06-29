package com.camper.camport.controller;

import com.camper.camport.dto.CamportDto;
import com.camper.camport.service.CamportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class CamportControllerTest {

    @Autowired
    private CamportService camportService;

    @Test
    public void findCamportsByMemberId() {
        List<CamportDto> camports = camportService.findCamportsByMemberId(1L);

        for (CamportDto camport : camports) {
            System.out.println("camport = " + camport);
        }
    }
}