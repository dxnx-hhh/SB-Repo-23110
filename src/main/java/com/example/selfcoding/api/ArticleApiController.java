package com.example.selfcoding.api;

import com.example.selfcoding.dto.ArticleForm;
import com.example.selfcoding.entity.Article;
import com.example.selfcoding.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleRepository articleRepository;

    //GET
    @GetMapping("/api/articles")
    private List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    //POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    //PATCH
    @PatchMapping("/api/articles/{id}")   //HTML을 이용하는 것이 아니기 때문에 PatchMapping을 사용할 수 있다!
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto) {
        //1. DTO -> Entity 변환
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        //2. 타깃 조회
        Article target = articleRepository.findById(id).orElse(null);

        //3. 잘못된 요청 처리하기
        if(target == null || id != article.getId()) {
            //잘못된 요청 응답(400)
            log.info("잘못된 요청! id= " + id + ", article= " + article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        //4. 업데이트 및 정상 응답(200) 반환
        target.patch(article);    //5. 기존 데이터에 새 데이터 붙이기(원래는 입력되지 않은 데이터는 null로 변환되기 때문에 이를 막기 위한 처리 필요)
        Article updated = articleRepository.save(target);  //6. 수정 내용 DB에 저장
            //정상 응답 반환
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        //1. 대상찾기
        Article target = articleRepository.findById(id).orElse(null);

        //2. 잘못된 요청 처리
        if(target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        //3. 대상 삭제
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
