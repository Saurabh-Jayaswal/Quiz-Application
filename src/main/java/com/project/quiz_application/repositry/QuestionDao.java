package com.project.quiz_application.repositry;

import com.google.gson.JsonObject;
import com.project.quiz_application.model.PairCategoryEntity;
import com.project.quiz_application.model.PairIdCategory;
import com.project.quiz_application.model.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface QuestionDao extends JpaRepository<QuestionModel , Integer> {

    List<QuestionModel> findByCategory(String category);

    @Query(value = "SELECT * FROM question_model q WHERE q.category =:category And q.difficulty_level=:difficulty ORDER BY RANDOM() LIMIT :count" , nativeQuery = true)
    List<QuestionModel> findRandomQuestionByCategoryDifficulty(Integer count ,String category,String difficulty);

    @Query(value = "SELECT * FROM question_model q WHERE q.difficulty_level=:difficulty ORDER BY RANDOM() LIMIT :count" , nativeQuery = true)
    List<QuestionModel> findRandomQuestionByDifficulty(Integer count ,String difficulty);

    @Query(value = "SELECT * FROM question_model q WHERE q.category =:category  ORDER BY RANDOM() LIMIT :count" , nativeQuery = true)
    List<QuestionModel> findRandomQuestionByCategory(Integer count ,String category);

    @Query(value = "SELECT * FROM question_model q  ORDER BY RANDOM() LIMIT :count" , nativeQuery = true)
    List<QuestionModel> findRandomQuestions(Integer count );

    @Query(value="SELECT category from question_model q ORDER BY id",nativeQuery = true)
    Set<String> findAllByCategory();
}
