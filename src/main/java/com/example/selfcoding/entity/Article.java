package com.example.selfcoding.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor     //[04.리팩터링] 생성자 자동생성 어노테이션
@ToString               //[04.리팩터링]toString 매서드 자동생성
@Entity
public class Article {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String content;


}
