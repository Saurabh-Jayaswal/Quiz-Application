package com.project.quiz_application.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.project.quiz_application.model.PairCategoryEntity;
import com.project.quiz_application.model.PairIdCategory;
import com.project.quiz_application.model.QuestionModel;
import com.project.quiz_application.repositry.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionService {
        @Autowired
        QuestionDao questionDao;
       public ResponseEntity<List<QuestionModel>> getQuestions(){
           try {
               return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
           }catch(Exception e){
               e.printStackTrace();
               return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
           }
       }

       public ResponseEntity<List<PairIdCategory>> getQuestionsByCategory(String category){
            try{
                List<QuestionModel> categoryList=questionDao.findByCategory(category);
                List<PairIdCategory> pairList=new ArrayList<>();
                for(QuestionModel questionModel:categoryList){
                    pairList.add(new PairIdCategory(questionModel.getId(),questionModel.getCategory()));
                }

                return new ResponseEntity<>(pairList , HttpStatus.OK);
            }catch(Exception e){
                e.printStackTrace();
                return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST);
            }
       }


    public ResponseEntity<String> addQuestion(QuestionModel questionModel) {
            try{
                questionDao.save(questionModel);
                return new ResponseEntity<>("Success", HttpStatus.CREATED);
            }catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
            }
    }

    public String deleteQuestion(Integer id) {
           questionDao.deleteById(id);
           return "deleted successfully";
    }

    public ResponseEntity< List<Map<String, String>>> getAllCategory() {
           try{
               Set<String> categoryList=questionDao.findAllByCategory();
               List<Map<String, String>> jsonList = new ArrayList<>();
               for (String item : categoryList) {
                   Map<String, String> itemMap = new HashMap<>();
                   itemMap.put("category", item);
                   jsonList.add(itemMap);
               }

               return new ResponseEntity<>(jsonList,HttpStatus.OK);
           }
           catch (Exception e){
               e.printStackTrace();
               return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
           }
    }
}
