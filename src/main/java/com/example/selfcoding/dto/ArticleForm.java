package com.example.selfcoding.dto;

import com.example.selfcoding.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor     //[04.리팩터링] 생성자 자동생성 어노테이션
@ToString               //[04.리팩터링]toString 매서드 자동생성
public class ArticleForm {
    private Long id;
    private String title;
    private String content;


    public Article toEntity() {
        return new Article(id, title, content);  // [07] 전에는 id필드가 없었기 때문에 null로 생성자를 호출했지만 위에 id 필드를 추가했으니 id 추가!
    }
}
