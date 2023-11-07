package com.example.selfcoding.controller;

import com.example.selfcoding.dto.ArticleForm;
import com.example.selfcoding.entity.Article;
import com.example.selfcoding.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

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

        log.info("form 데이터: " + form.toString());

        Article article = form.toEntity();
        log.info("DTO -> Entity 변환 로그: " + article.toString());     //[04.리팩터링] 아래 println을 로깅코드로 대체 (DTO가 엔티티로 잘 변환되는지 확인)
        Article saved = articleRepository.save(article);


        log.info("DB 저장 확인 로그: " + saved.toString());      //[04.리팩터링] 아래 println을 로깅코드로 대체 (article이 DB에 잘 저장되는지 확인)

        return "redirect:/articles/" + saved.getId();
    }


    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {

        log.info("id = " + id); //id를 잘 받았는지 확인하는 로그 찍기
        
        //1. id를 조회해 데이터 가져오기 (id를 찾고 없으면 null값 반환)
        Article articleEntity = articleRepository.findById(id).orElse(null);

        //2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);

        //3. 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {

        //1. 모든 데이터 가져오기 (레포지토리에서 findAll()에 대한 재정의 후 사용함)
        ArrayList<Article> articleEntityList = articleRepository.findAll();

        //2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);

        //3. 뷰 페이지 설정하기
        return "articles/index";
    }




}
