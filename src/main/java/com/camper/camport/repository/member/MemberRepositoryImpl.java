package com.camper.camport.repository.member;

import com.camper.camport.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepositoryImpl implements MemberRepositoryCustom{
    /*
    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
    */

    @Override
    public List<Member> serch(String name) {
        //Querydsl
        return null;
    }
}
