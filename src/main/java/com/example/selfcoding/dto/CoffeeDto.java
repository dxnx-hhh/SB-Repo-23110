package com.example.selfcoding.dto;

import com.example.selfcoding.entity.Coffee;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class CoffeeDto {

    private Long id;
    private String name;
    private String price ;

    public Coffee toEntity() {
        return new Coffee(id, name, price);
    }

}
