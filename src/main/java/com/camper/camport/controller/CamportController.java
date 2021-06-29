package com.camper.camport.controller;

import com.camper.camport.dto.CamportDto;
import com.camper.camport.dto.CamportSearchCondition;
import com.camper.camport.entity.Camport;
import com.camper.camport.entity.enumerated.ApprovalStatusToCamport;
import com.camper.camport.service.CamportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/camport")
@RequiredArgsConstructor
public class CamportController {
    private final CamportService camportService;

    // 생성
    @PostMapping("")
    public void createCamport(@RequestBody Camport camport) {
        camportService.saveCamport(camport);
    }

    /**
     *
     * @param condition
     * @param pageable
     * @return
     * http://localhost:8090/api/camport?page=0&size=10
     */
    // 캠포트 목록조회 with paging
    @GetMapping("")
    public Page<CamportDto> searchCamports(CamportSearchCondition condition, Pageable pageable) {
        return camportService.searchCamportWithPaging(condition, pageable);
    }

    // 승인 신청
    @PatchMapping("/{memberId}/{status}")
    public void setApprovalStatus(@PathVariable Long memberId, @PathVariable ApprovalStatusToCamport status) {
        camportService.setApprovalStatus(memberId, status);
    }

    // 본인의 전체 캠포트 조회
    @GetMapping("/{memberId}")
    public List<CamportDto> findCamportsByMemberId(@PathVariable Long memberId) {
        return camportService.findCamportsByMemberId(memberId);
    }

    // 상세조회(추가: 세션으로 본인 확인)
    @GetMapping("/{memberId}/{camportId}")
    public CamportDto findCamportById(CamportSearchCondition camportSearchCondition) {
        return camportService.findCamportById(camportSearchCondition);
    }

    // 수정
    @PutMapping("")
    public void updateCamport(@RequestBody Camport camport) {
        camportService.saveCamport(camport);
    }

    // 삭제
    @DeleteMapping("/{camportId}")
    public void deleteCamport(@PathVariable Long camportId) {
        camportService.deleteCamport(camportId);
    }

}
