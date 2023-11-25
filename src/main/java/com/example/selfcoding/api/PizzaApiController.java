package com.example.selfcoding.api;

import com.example.selfcoding.dto.PizzaDto;
import com.example.selfcoding.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaApiController {
    @Autowired
    private PizzaService pizzaService;

    //1. 피자 조회
    @GetMapping("/api/pizza")
    public ResponseEntity<List<PizzaDto>> index() {
        List<PizzaDto> dtos = pizzaService.index();

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    //2. 피자 메뉴 추가
    @PostMapping("/api/pizza")
    public ResponseEntity<PizzaDto> create(@RequestBody PizzaDto dto) {
        PizzaDto createdDto = pizzaService.create(dto);

        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    //3. 피자 메뉴 수정
    @PatchMapping("/api/pizza/{id}")
    public ResponseEntity<PizzaDto> update(@PathVariable Long id, @RequestBody PizzaDto dto) {
        PizzaDto updatedDto = pizzaService.update(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    //4. 피자 메뉴 삭제
    @DeleteMapping("/api/pizza/{id}")
    public ResponseEntity<PizzaDto> delete(@PathVariable Long id) {
        PizzaDto deleteDto = pizzaService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(deleteDto);
    }

}
