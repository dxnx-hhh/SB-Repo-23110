package com.example.selfcoding.service;

import com.example.selfcoding.dto.PizzaDto;
import com.example.selfcoding.entity.Pizza;
import com.example.selfcoding.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    public List<PizzaDto> index() {
        return pizzaRepository.findAll()
                .stream()
                .map(pizza -> PizzaDto.createPizzaDto(pizza))
                .collect(Collectors.toList());
    }

    @Transactional
    public PizzaDto create(PizzaDto dto) {
        //댓글 엔티티 생성
        Pizza pizza = Pizza.createPizza(dto);

        //댓글 DB에 저장
        Pizza created = pizzaRepository.save(pizza);

        //DTO로 변환해 반환
        return PizzaDto.createPizzaDto(created);

    }

    @Transactional
    public PizzaDto update(Long id, PizzaDto dto) {
        //피자 메뉴 조회 및 예외 발생
        Pizza target = pizzaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("메뉴 수정 실패. 메뉴 넘버가 없습니다"));

        //메뉴 수정
        target.patch(dto);

        //DB로 갱신
        Pizza updated = pizzaRepository.save(target);

        //피자 엔티티를 DTO로 변환 및 반환
        return PizzaDto.createPizzaDto(updated);
    }

    public PizzaDto delete(Long id) {
        //메뉴 조회 및 예외 발생
        Pizza target = pizzaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("메뉴 삭제 실패. 없는 메뉴입니다."));

        //메뉴 삭제
        pizzaRepository.delete(target);

        //삭제 메뉴를 DTO로 변환 및 반환
        return PizzaDto.createPizzaDto(target);
    }
}
