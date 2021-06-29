package com.camper.camport.service;

import com.camper.camport.dto.CamportDto;
import com.camper.camport.entity.CampLocation;
import com.camper.camport.entity.Camport;
import com.camper.camport.entity.Member;
import com.camper.camport.entity.enumerated.ApprovalStatusToCamport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
class CamportServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private MemberService memberService;

    @Autowired
    private CamportService camportService;

    @Test
    @Commit
    public void 캠포트생성() {
        CampLocation campLocation1 = new CampLocation();
        campLocation1.setCampLocationName("포항캠핑장");
        campLocation1.setCampLocationAddress("포항시");
        em.persist(campLocation1);

        Member member1 = new Member();
        member1.setName("양홍석");

        Camport camport1 = new Camport();
        camport1.setCampLocation(campLocation1);
        camport1.setMember(member1);

        memberService.join(member1);

        Optional<Member> memberResult = memberService.findOne(1L);
        System.out.println(em.find(CampLocation.class, 1L));

    }

    @Test
    @Commit
    public void 승인상태변경() {
        camportService.setApprovalStatus(1L, ApprovalStatusToCamport.approved);
    }

//    @Test
//    public void 상세조회() {
//        Optional<Camport> camport = camportService.findCamportById(1L);
//        System.out.println(camport.get().getCamportId());
//    }
//
//    @Test
//    public void 수정() {
//        Camport camport1 = camportService.findCamportById(1L).get();
//
//        camport1.setCaption("포항 게시물 문구 수정");
//        camportService.saveCamport(camport1);
//
//        Camport result = camportService.findCamportById(1L).get();
//
//        assertThat(result).isEqualTo(camport1);
//    }

    @Test
    public void 조회() {
        List<CamportDto> camport = camportService.findCamportsByMemberId(1L);
        for (CamportDto camportDto : camport) {
            System.out.println("camportDto = " + camportDto);
        }
    }

    @Commit
    public void 수정() {
//        Camport camport = camportService.findOne(2L).get();
//        camport.setCaption("놀러갔다왔어요~~~~~~~~");
        Member member = memberService.findOne(1L).get();
        CampLocation campLocation = new CampLocation();
        campLocation.setCampLocationName("합천 캠핑장1");
        campLocation.setCampLocationAddress("합천시");

        em.persist(campLocation);

        Camport camport = new Camport();
        camport.setId(1L);
        camport.setMember(member);
        camport.setCampLocation(campLocation);

        camportService.saveCamport(camport);

    }

}