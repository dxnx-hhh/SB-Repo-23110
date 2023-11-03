package com.example.selfcoding.dto;

import com.example.selfcoding.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor     //[04.리팩터링] 생성자 자동생성 어노테이션
@ToString               //[04.리팩터링]toString 매서드 자동생성
public class ArticleForm {
    private String title;
    private String content;


    public Article toEntity() {
        return new Article(null, title, content);
    }
}
