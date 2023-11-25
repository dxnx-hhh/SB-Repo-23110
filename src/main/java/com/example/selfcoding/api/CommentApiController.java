package com.example.selfcoding.api;

import com.example.selfcoding.dto.CommentDto;
import com.example.selfcoding.entity.Comment;
import com.example.selfcoding.service.CommentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//[14장] 댓글 CRUD 구현
@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;
    
    //1. 댓글 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        //서비스에 결과 위임
        List<CommentDto> dtos = commentService.comments(articleId);

        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    //2. 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto) {
        //서비스에 위임
        CommentDto createdDto = commentService.create(articleId, dto);

        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }


    //3. 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto) {
        //서비스에 위임
        CommentDto updatedDto = commentService.update(id, dto);

        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }


    //4. 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
        //서비스에 위임
        CommentDto deleteDto = commentService.delete(id);

        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deleteDto);
    }





}
