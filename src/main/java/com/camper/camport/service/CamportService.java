package com.camper.camport.service;

import com.camper.camport.dto.CamportDto;
import com.camper.camport.dto.CamportSearchCondition;
import com.camper.camport.entity.Camport;
import com.camper.camport.entity.enumerated.ApprovalStatusToCamport;
import com.camper.camport.repository.camport.CamportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CamportService {
    private final CamportRepository camportRepository;

    @Autowired
    public CamportService(CamportRepository camportRepository) {
        this.camportRepository = camportRepository;
    }

    // 캠포트 생성
    public void saveCamport(Camport camport) {
        // 중복체크

        // 생성
        camportRepository.save(camport);
    }

    // 캠포트 목록 조회 with paging
    public Page<CamportDto> searchCamportWithPaging(CamportSearchCondition condition, Pageable pageable) {
        Page<CamportDto> results = null;
        
        if (condition.getMemberId() == null) {
            // 회원 전체단위
            results = camportRepository.searchCamportWithPagingComplex(condition, pageable);
        } else {
            // 회원단위
            results = camportRepository.searchCamportsWithPaging(condition, pageable);
        }
        
        return results;
    }

    // 승인상태 설정
    public void setApprovalStatus(Long memberId, ApprovalStatusToCamport status) {
        camportRepository.setApprovalStatus(memberId, status);
    }

    // 본인 캠포트 전체 조회
    public List<CamportDto> findCamportsByMemberId(Long memberId) {
        CamportSearchCondition camportSearchCondition = new CamportSearchCondition(memberId);
        return camportRepository.findCamportsByMemberId(camportSearchCondition);
    }

    // 상세조회
    public CamportDto findCamportById(CamportSearchCondition camportSearchCondition) {
        return camportRepository.findCamportsByMemberId(camportSearchCondition).get(0);
    }

    // 단건 조회
    public Optional<Camport> findOne(Long id) {
        return camportRepository.findById(id);
    }

    // 삭제
    public void deleteCamport(Long camportId) {
        camportRepository.deleteById(camportId);
    }
}
