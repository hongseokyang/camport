package com.camper.camport.repository.camport;

import com.camper.camport.dto.CamportDto;
import com.camper.camport.dto.CamportSearchCondition;
import com.camper.camport.entity.enumerated.ApprovalStatusToCamport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CamportRepositoryCustom {
    // 각 회원의 camport 리스트 조회 (페이징 X)
    List<CamportDto> findCamportsByMemberId(CamportSearchCondition condition);

    // camport 승인 상태값 변경
    void setApprovalStatus(Long memberId, ApprovalStatusToCamport status);

    // fetchResults() 사용, 회원단위 검색
    Page<CamportDto> searchCamportsWithPaging(CamportSearchCondition condition, Pageable pageable);

    // 전체 회원단위 검색
    Page<CamportDto> searchCamportWithPagingComplex(CamportSearchCondition condition, Pageable pageable);
}
