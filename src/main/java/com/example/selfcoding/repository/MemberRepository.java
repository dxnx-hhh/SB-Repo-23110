package com.example.selfcoding.repository;

import com.example.selfcoding.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
