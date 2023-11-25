package com.example.selfcoding.entity;

import com.example.selfcoding.dto.PizzaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String price;

    public static Pizza createPizza(PizzaDto dto) {
        if(dto.getId() != null)
            throw new IllegalArgumentException("이미 존재하는 메뉴 넘버입니다.");

        return new Pizza(
                dto.getId(),
                dto.getName(),
                dto.getPrice()
        );
    }

    public void patch(PizzaDto dto) {
        //예외 발생
        if(this.id != dto.getId())
            throw new IllegalArgumentException("메뉴 수정 실패. 잘못된 메뉴 넘버가 입력되었습니다.");

        //객체 갱신
        if(dto.getName() != null)
            this.name = dto.getName();
        if(dto.getPrice() != null)
            this.price = dto.getPrice();
    }
}
