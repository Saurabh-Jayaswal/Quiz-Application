package com.project.quiz_application.model;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PairIdCategory {
    Integer id;
    String category;

    public PairIdCategory(Integer id, String category) {
        this.id = id;
        this.category = category;
    }
}
