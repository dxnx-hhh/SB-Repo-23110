package com.example.selfcoding.service;

import com.example.selfcoding.dto.ArticleForm;
import com.example.selfcoding.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest  //[13장] 해당 클래스를 스프링 부트와 연동해 테스트
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test //[13장] 해당 메서드가 테스트 코드임을 선언
    void index() {
        //1. 예상 데이터
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");

        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c)); //a, b, c를 합침
        //2. 실제 데이터
        List<Article> articles = articleService.index();

        //3. 비교 및 검증
        //JUnit에서 제공하는 assertEquals(x, y): 예상 데이터(x)와 비교 데이터(y)가 일치하면 테스트를 통과시킨다.
        assertEquals(expected.toString(), articles.toString());

    }

    @Test
    void show_성공_존재하는_id_입력() {
        //1. 예상 데이터 저장
        Long id = 1L;
        Article expected = new Article(1L, "가가가가", "1111");

        //2. 실제 데이터 저장
        Article article = articleService.show(id);

        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패_존재하지_않는_id_입력() {
        //1. 예상 데이터(null) 저장
        Long id = -1L;
        Article expected = null;

        //2. 실제 데이터 저장
        Article article = articleService.show(id);

        //3. 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_성공_title과_content만_있는_dto_입력() {
        //1. 예상 데이터
        //title과 content 임의 값 배정
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content); //dto 생성
        Article expected = new Article(4L, title, content);

        //2. 실제 데이터
        Article article = articleService.create(dto);

        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void create_실패_id가_포함된_dto_입력() {
        //1. 예상 데이터
        //id, title, content 임의 값 배정
        Long id = 4L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title, content); //dto 생성
        Article expected = null;

        //2. 실제 데이터
        Article article = articleService.create(dto);

        //3. 비교 및 검증
        assertEquals(expected, article);
    }

    //데이터를 조회(Read)하는 테스트를 제외하고 데이터를 생성(Create), 수정(Update), 삭제(Delete)하는 테스트를 할 때는
    // 반드시 해당 테스트를 트랜잭션(@Transaction)으로 묶어 테스트가 종료한 후 원래대로 돌아갈 수 있게 롤백 처리해줘야 한다.

    @Test
    @Transactional
    void update_성공_존재하는_id와_title_content가_있는_dto_입력() {
        //1. 예상 데이터
        Long id = 1L;
        String title = "가나다라";
        String content = "1234";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title,content);
        //2. 실제 데이터
        Article article = articleService.update(id, dto);
        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title만_있는_dto_입력() {
        //1. 예상 데이터
        Long id = 1L;
        String title = "AAAA";
        String content = null;
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(1L, "AAAA" , "1111");
        //2. 실제 데이터
        Article article = articleService.update(id, dto);
        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_실패_존재하지_않는_id의_dto_입력() {
        //1. 예상 데이터
        Long id = -1L;
        String title = "가나다라";
        String content = "1234";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        //2. 실제 데이터
        Article article = articleService.update(id, dto);
        //3. 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void delete_성공_존재하는_id_입력() {
        //1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");
        //2. 실제 데이터
        Article article = articleService.delete(id);
        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void delete_실패_존재하지_않는_id_입력() {
        //1. 예상 데이터
        Long id = -1L;
        Article expected = null;
        //2. 실제 데이터
        Article article = articleService.delete(id);
        //3. 비교 및 검증
        assertEquals(expected, article);
    }




}