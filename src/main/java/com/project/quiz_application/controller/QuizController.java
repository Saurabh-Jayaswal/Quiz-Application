package com.project.quiz_application.controller;


import com.project.quiz_application.model.QuestionWrapper;

import com.project.quiz_application.model.Response;
import com.project.quiz_application.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("quiz")

public class QuizController{
    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<List<QuestionWrapper>> createQuiz(@RequestParam String category , @RequestParam int count ,@RequestParam String difficulty){
        ResponseEntity<String> quizGetById =quizService.createQuiz(category , count, difficulty);
        Integer getIdQuiz =Integer.parseInt(quizGetById.getBody());
         return getQuizQuestions(getIdQuiz);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizById(id);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<String> submitQuiz(@PathVariable Integer id , @RequestBody List<Response> responses){
        return quizService.checkResponse(id , responses);
    }
}
