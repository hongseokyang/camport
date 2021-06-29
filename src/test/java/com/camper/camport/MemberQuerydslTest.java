package com.camper.camport;

import com.camper.camport.entity.Member;
import com.camper.camport.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberQuerydslTest {

    @Autowired
    EntityManager em;

    @Test
    public void 회원검색() {
        em.clear();

        Member member = new Member();
        em.persist(member);

        JPAQueryFactory query = new JPAQueryFactory(em);
//        QMember qMember = new QMember("m");
        QMember qMember = QMember.member;

        Member result = query
                .selectFrom(qMember)
                .fetchOne();

        assertThat(result).isEqualTo(member);
        assertThat(member.getId()).isEqualTo(result.getId());
    }
}
