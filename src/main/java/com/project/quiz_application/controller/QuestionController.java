package com.project.quiz_application.controller;

import com.google.gson.JsonObject;
import com.project.quiz_application.model.PairCategoryEntity;
import com.project.quiz_application.model.PairIdCategory;
import com.project.quiz_application.model.QuestionModel;
import com.project.quiz_application.service.QuestionService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/question/")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<QuestionModel>> getAllQuestions(){
        return questionService.getQuestions();
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<PairIdCategory>> getQuestionByCategory(@PathVariable String category){

        return questionService.getQuestionsByCategory(category);
    }
    @GetMapping("getAllCategory")
    public ResponseEntity< List<Map<String, String>>> getAllCategory(){
        return questionService.getAllCategory();
    }
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody QuestionModel questionModel){
        return questionService.addQuestion(questionModel);
    }
    @DeleteMapping("delete/{id}")
    public String deleteQuestion(@PathVariable Integer id){
        return questionService.deleteQuestion(id);
    }
}
