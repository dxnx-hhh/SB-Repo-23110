package com.example.selfcoding.dto;


import com.example.selfcoding.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MemberForm {
    private Long id;
    private String email;
    private String password;


    public Member toEntity() {
        return new Member(id, email, password);
    }
}
