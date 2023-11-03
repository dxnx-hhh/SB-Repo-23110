package com.example.selfcoding.controller;

import com.example.selfcoding.dto.ArticleForm;
import com.example.selfcoding.entity.Article;
import com.example.selfcoding.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
        System.out.println(form.toString());
        Article article = form.toEntity();
        System.out.println(article.toString()); //DTO가 엔티티로 잘 변환되는지 확인
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString()); //article이 DB에 잘 저장되는지 확인
        return "";
    }
}
