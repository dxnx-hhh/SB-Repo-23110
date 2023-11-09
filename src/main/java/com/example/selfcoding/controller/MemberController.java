package com.example.selfcoding.controller;

import com.example.selfcoding.dto.MemberForm;
import com.example.selfcoding.entity.Article;
import com.example.selfcoding.entity.Member;
import com.example.selfcoding.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
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
        return "redirect:/members/" + saved.getId();
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model) {

        log.info("id = " + id); //id를 잘 받았는지 확인하는 로그 찍기

        //1. id를 조회해 데이터 가져오기 (id를 찾고 없으면 null값 반환)
        Member memberEntity = memberRepository.findById(id).orElse(null);

        //2. 모델에 데이터 등록하기
        model.addAttribute("member", memberEntity);

        //3. 뷰 페이지 반환하기
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model) {

        //1. 모든 데이터 가져오기 (레포지토리에서 findAll()에 대한 재정의 후 사용함)
        ArrayList<Member> memberEntityList = memberRepository.findAll();

        //2. 모델에 데이터 등록하기
        model.addAttribute("memberList", memberEntityList);

        //3. 뷰 페이지 설정하기
        return "members/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);

        return "members/edit";
    }

   @PostMapping("/members/update")
    public String update(MemberForm form) {

        Member memberEntity = form.toEntity();
        log.info(form.toString());

       Member target= memberRepository.findById(memberEntity.getId()).orElse(null);
       if(target != null) {
           memberRepository.save(memberEntity);
       }

       return "redirect:/members/" + memberEntity.getId();
   }










}
