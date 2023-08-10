package com.project.quiz_application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PairCategoryEntity {
    @Id

    private Integer id;
    private String category;
}
