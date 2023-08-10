package com.project.quiz_application.service;

import com.project.quiz_application.model.QuestionModel;
import com.project.quiz_application.model.QuestionWrapper;
import com.project.quiz_application.model.Quiz;
import com.project.quiz_application.model.Response;
import com.project.quiz_application.repositry.QuestionDao;
import com.project.quiz_application.repositry.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

@Service
public class QuizService {


    @Autowired
    QuestionDao questionDao;
    @Autowired
    QuizDao quizDao;
    public ResponseEntity<String> createQuiz(String category , int count ,String difficulty ) {
        List<QuestionModel> questions;
        if(!difficulty.equals("All") && !category.equals("All")){
            questions=questionDao.findRandomQuestionByCategoryDifficulty(count , category, difficulty);
        }
        else if(!difficulty.equals("All")){
            questions=questionDao.findRandomQuestionByDifficulty(count , difficulty);
        }
        else if(!category.equals(("All"))){
            questions=questionDao.findRandomQuestionByCategory(count , category);
        }
       else {
            questions = questionDao.findRandomQuestions(count);
       }



        Quiz quiz = new Quiz();
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>(quiz.getId().toString(), HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizById(Integer id) {
        try{
            Optional<Quiz> quiz = quizDao.findById(id);
            List<QuestionModel> questionsFromDB = quiz.get().getQuestions();
            List<QuestionWrapper> questionsForUser = new ArrayList<>();
            for(QuestionModel question : questionsFromDB){
                QuestionWrapper qn = new QuestionWrapper(question.getId() , question.getQuestionTitle() , question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4(),question.getRightAnswer());
                questionsForUser.add(qn);
            }
            return new ResponseEntity<>(questionsForUser , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> checkResponse(Integer id, List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<QuestionModel> questions = quiz.get().getQuestions();
        int right = 0 ;
        int i = 0;
        TreeSet<String> right_Answer_Set=new TreeSet<>();
        for(QuestionModel questionModel:questions){
            right_Answer_Set.add(questionModel.getRightAnswer());
        }

        for(Response response : responses){
            System.out.println(response.getResponse()+" "+(questions.get(i).getRightAnswer()));
            String to_Check_Response=response.getResponse();
            if(right_Answer_Set.contains(to_Check_Response)){
                right++;
                right_Answer_Set.remove(to_Check_Response);
            }
            i++;
        }
        System.out.println(right);
        return new ResponseEntity<>("Total Correct answers  : "+right , HttpStatus.OK);
    }
}
