package com.camper.camport.controller;

import com.camper.camport.entity.Member;
import com.camper.camport.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @GetMapping(value = "/members/find")
    @ResponseBody
    public Member selectMember(Long id) {
        return memberService.findOne(id).get();
    }
}
