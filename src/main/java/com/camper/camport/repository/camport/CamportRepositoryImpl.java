package com.camper.camport.repository.camport;

import com.camper.camport.dto.CamportDto;
import com.camper.camport.dto.CamportSearchCondition;
import com.camper.camport.entity.Camport;
import com.camper.camport.entity.enumerated.ApprovalStatusToCamport;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.camper.camport.entity.QCampLocation.campLocation;
import static com.camper.camport.entity.QCamport.camport;
import static com.camper.camport.entity.QMember.member;

@Repository
public class CamportRepositoryImpl implements CamportRepositoryCustom {

    private final EntityManager em; // spring 에서 프록시를 주입 : 트랜잭션 단위로 다른 EntityManager 를 사용하도록 바인딩
    private final JPAQueryFactory queryFactory;

    @Autowired
    public CamportRepositoryImpl(EntityManager em, JPAQueryFactory queryFactory) {
        this.em = em;
        this.queryFactory = queryFactory;
    }

    @Override
    public void setApprovalStatus(Long memberId, ApprovalStatusToCamport status) {

        queryFactory.update(camport)
                .where(camport.member.id.eq(memberId))
                .set(camport.status, status)
                .execute();
    }

    // memberId 비교 조건절
    public BooleanExpression memberIdEq(Long memberIdCond) {
        return memberIdCond != null ? member.id.eq(memberIdCond) : null;
    }

    // camportId 비교 조건절
    public BooleanExpression camportIdEq(Long camportIdCond) {
        return camportIdCond != null ? camport.id.eq(camportIdCond) : null;
    }

    // 각 회원의 camport 리스트 조회 (페이징 X)
    @Override
    public List<CamportDto> findCamportsByMemberId(CamportSearchCondition condition) {

        List<CamportDto> camports = queryFactory
                .select(Projections.fields(CamportDto.class,
                        camport.id,
                        member.id,
                        campLocation.campLocationId,
                        campLocation.campLocationName,
                        campLocation.campLocationAddress))
                .from(camport)
                .join(camport.member, member)
                .leftJoin(camport.campLocation, campLocation)
                .where(memberIdEq(condition.getMemberId()),
                        camportIdEq(condition.getCamportId()))
                .fetch();

        return camports;
    }

    // fetchResults() 사용, 회원단위 검색
    @Override
    public Page<CamportDto> searchCamportsWithPaging(CamportSearchCondition condition, Pageable pageable) {
        QueryResults<CamportDto> results = getCamportsQueryResults(condition, pageable).fetchResults();

        List<CamportDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    // 전체 회원단위 검색
    @Override
    public Page<CamportDto> searchCamportWithPagingComplex(CamportSearchCondition condition, Pageable pageable) {
        List<CamportDto> content = getCamportsQueryResults(condition, pageable).fetch();
        JPAQuery<Camport> countQuery = getCamportsCountQuery(condition, pageable);

        /**
         * count 쿼리가 생략 가능한 경우 생략해서 처리
         *  - 페이지 시작이면서 컨텐츠 사이즈가 페이지 사이즈보다 작을 때
         *  - 마지막 페이지 일 때 (offset + 컨텐츠 사이즈를 더해서 전체 사이즈 구함)
         */
        //return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetchCount());
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private JPAQuery<Camport> getCamportsCountQuery(CamportSearchCondition condition, Pageable pageable) {
        // 검색조건 추가 필요 (위치, 검색어)
        JPAQuery<Camport> countQuery = queryFactory
                .selectFrom(camport);

        return countQuery;
    }

    private JPAQuery<CamportDto> getCamportsQueryResults(CamportSearchCondition condition, Pageable pageable) {
        // 검색조건 추가 필요 (위치, 검색어)
        JPAQuery<CamportDto> query = queryFactory
                .select(Projections.fields(CamportDto.class,
                        camport.id.as("camportId"),
                        member.id.as("memberId"),
                        campLocation.campLocationId,
                        campLocation.campLocationName,
                        campLocation.campLocationAddress))
                .from(camport)
                .join(camport.member, member)
                .leftJoin(camport.campLocation, campLocation)
                .where(memberIdEq(condition.getMemberId()),
                        camportIdEq(condition.getCamportId()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        return query;
    }
}
