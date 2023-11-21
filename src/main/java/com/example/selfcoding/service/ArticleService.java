package com.example.selfcoding.service;

import com.example.selfcoding.dto.ArticleForm;
import com.example.selfcoding.entity.Article;
import com.example.selfcoding.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service //[12장] 서비스 객체 생성
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;


    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null) { //[12장] 이미 데이터가 있는 id라면 코드 400 반환
            return null;
        }
        return articleRepository.save(article);
    }


    public Article update(Long id, ArticleForm dto) {
        //1. DTO -> Entity 변환
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        //2. 타깃 조회
        Article target = articleRepository.findById(id).orElse(null);

        //3. 잘못된 요청 처리하기
        if(target == null || id != article.getId()) {
            //잘못된 요청 응답(400)
            log.info("잘못된 요청! id= " + id + ", article= " + article.toString());
            return null;
        }

        //4. 업데이트 및 수정 데이터 반환
        target.patch(article);
        Article updated = articleRepository.save(target);
        //[12장] 정상 응답은 컨트롤러만 하므로, 서비스에선 수정 데이터만 반환
        return updated;
    }

    public Article delete(Long id) {
        //1. 대상찾기
        Article target = articleRepository.findById(id).orElse(null);

        //2. 잘못된 요청 처리
        if(target == null) {
            return null;
        }

        //3. 대상 삭제
        articleRepository.delete(target);
        return target;
    }

    //서비스 수행 도중 강제예외가 발생했더라도 예외 발생 전 입력된 3개의 데이터는 저장된다.
    //단, 아래처럼 트랜잭션 어노테이션을 붙여 서비스 수행 도중 예외 발생 시 해당 건 전체를 롤백시킨다.
    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        //1. dto 묶음을 엔티티 묶음으로 변환하기
          //1-1. dto의 묶음인 dtos를 엔티티로 만들기 위해 스트림(stream) 문법 사용
          //1-2. map()으로 dto가 하나하나 올 떄마다 dto.toEntity()를 수행해 매핑
          //1-3. 이렇게 매핑한 것을 리스트로 묶습니다
          //1-4. 최종 결과를 articleList에 저장
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        //2. 엔티티 묶음을 DB에 저장하기
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        //3. 강제 예외 발생시키기
        articleRepository.findById(-1L)  //id가 -1인 데이터 찾기
                .orElseThrow(() -> new IllegalArgumentException("결제 실패!")); //찾는 데이터가 없으면 예외 발생

        //4. 결과 값 반환하기
          //강제로 예외가 발생하도록 했지만 형식상 articleList 반환
        return articleList;

    }
}
