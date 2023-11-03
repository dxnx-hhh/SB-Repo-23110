package com.example.selfcoding.controller;

import com.example.selfcoding.dto.MemberForm;
import com.example.selfcoding.entity.Member;
import com.example.selfcoding.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/members")
    public String newMember() {
        return "members/new";
    }

    @PostMapping("/join")
    public String joinMember(MemberForm form) {
        System.out.println(form.toString());
        Member member = form.toEntity();
        System.out.println(member.toString());
        Member saved = memberRepository.save(member);
        System.out.println(saved.toString());
        return "";
    }
}
