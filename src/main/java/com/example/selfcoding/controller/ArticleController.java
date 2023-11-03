package com.example.selfcoding.controller;

import com.example.selfcoding.dto.ArticleForm;
import com.example.selfcoding.entity.Article;
import com.example.selfcoding.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j     //[04.리팩터링] 로깅 기능을 위한 어노테이션
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());     //[04.리팩터링] 아래 println을 로깅코드로 대체 (form데이터 잘 받았는지)
//        System.out.println(form.toString());
        Article article = form.toEntity();
        log.info(article.toString());     //[04.리팩터링] 아래 println을 로깅코드로 대체 (DTO가 엔티티로 잘 변환되는지 확인)
//        System.out.println(article.toString());
        Article saved = articleRepository.save(article);
        log.info(saved.toString());      //[04.리팩터링] 아래 println을 로깅코드로 대체 (article이 DB에 잘 저장되는지 확인)
//        System.out.println(saved.toString());
        return "";
    }
}
