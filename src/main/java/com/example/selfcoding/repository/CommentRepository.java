package com.example.selfcoding.repository;

import com.example.selfcoding.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //[14장] 특정 게시글의 모든 댓글 조회. @Quary 어노테이션
    //value 속성에 실행하려는 쿼리 작성
    @Query(value =
            "SELECT * " +
            "FROM comment " +
            "WHERE article_id = :articleId",
            nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);

    //[14장] 특정 닉네임의 모든 댓글 조회. orm.xml 파일
    List<Comment> findByNickname(String nickname);

}
