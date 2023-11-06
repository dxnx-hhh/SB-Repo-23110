package com.example.selfcoding.repository;

import com.example.selfcoding.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {

//원래의 이 코드를 아래와 같이 재정의함
//    @Override
//    Iterable<Article> findAll();

    @Override  //상위클래스가 가지고 있는 메서드를 하위 클래스가 재정의해서 사용함
    ArrayList<Article> findAll();
}
