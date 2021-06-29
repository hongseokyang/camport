package com.camper.camport.repository.member;

import com.camper.camport.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> serch(String name);
}
