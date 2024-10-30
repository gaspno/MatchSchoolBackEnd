package com.mathschool.MathSchoolBack.endpoints;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.database.annotations.NotNull;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mathschool.MathSchoolBack.entities.StudentEntity;
import com.mathschool.MathSchoolBack.entities.WatchedClassEntity;
import com.mathschool.MathSchoolBack.model.*;
import com.mathschool.MathSchoolBack.services.FirebaseService;
import com.mathschool.MathSchoolBack.services.ServiceRetrieveData;
import com.mathschool.MathSchoolBack.services.ServiceStudentClassRegister;
import com.mathschool.MathSchoolBack.services.ServiceYearAndSubjectRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("content")
public class ContentController {


    @Autowired
    private ServiceYearAndSubjectRegister serviceYearAndSubjectRegister;
    @Autowired
    private ServiceStudentClassRegister serviceClassRegister;
    @Autowired
    private FirebaseService firebaseService;
    @Autowired
    private ServiceRetrieveData serviceRetrieveData;



    @PostMapping("year")
    private String addYear(@RequestParam("year")@NotNull String name, RedirectAttributes redirectAttributes){
        SchoolYear schoolYear= serviceYearAndSubjectRegister.addYear(name);
        redirectAttributes.addFlashAttribute("successMessage",schoolYear.getName());
        return "redirect:/control/year";
    }
    @PostMapping("subject")
    private String addSubject(@RequestParam("year")@NotNull String yearName,@RequestParam("subject") @NotNull String subjectName,@RequestParam("image") MultipartFile file,RedirectAttributes redirectAttributes) throws IOException {
        String imageURl=firebaseService.uploadFile(file,"subjects");
        Subject subject= serviceYearAndSubjectRegister.addSubject(yearName,subjectName,imageURl);
        redirectAttributes.addFlashAttribute("successMessage",subject.getName());
        return "redirect:/control/subject";
    }




    @PostMapping("test")
    private  String test(@RequestParam("subject")@NotNull String subjectName,@RequestParam("questions")@NotNull String questions,RedirectAttributes redirectAttributes){
        System.out.println(questions);
        List<Question> questionsList=new ArrayList<>();
        Iterator<JsonElement> iterator=new JsonParser().parse(questions).getAsJsonArray().iterator();
        while (iterator.hasNext()){
            JsonElement jsonElement= iterator.next();
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String key = jsonObject.keySet().iterator().next();
            JsonElement valueElement = jsonObject.get(key);
            JsonObject objectJson= valueElement.getAsJsonObject();
            Question q=new Question();
            q.setQuestion(objectJson.get("enunciado").getAsString());
            q.setOptions(new String[]{
                    objectJson.get("alternativa1").getAsString(),
                    objectJson.get("alternativa2").getAsString(),
                    objectJson.get("alternativa3").getAsString(),
                    objectJson.get("alternativa4").getAsString(),

            });
            q.setIndexCorrectAnswer(objectJson.get("correctnswer").getAsInt());
            questionsList.add(q);
        }
        serviceYearAndSubjectRegister.addTest( subjectName ,questionsList);


        redirectAttributes.addFlashAttribute("successMessage", subjectName);
        return "redirect:/control/test";
    }


    @PostMapping("class")
    private String addClass(@RequestParam("subject")@NotNull String subjectName, @RequestParam("class_name")@NotNull String className ,@RequestParam("content_urls")@NotNull String urls,@RequestParam(value="files",required = false)MultipartFile[] files, RedirectAttributes redirectAttributes) throws IOException {


        JsonArray jsonArray=new JsonParser().parse(urls).getAsJsonArray();
        List<String> resourcesList=new ArrayList<>();
        int i=0;
        for(JsonElement jsonElement:jsonArray){

            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String key = jsonObject.keySet().iterator().next();
            JsonElement valueElement = jsonObject.get(key);

            if (key.equals("video")){
                String videoUrl= firebaseService.uploadFile(files[i],"videos");
                resourcesList.add(key + " " + videoUrl);
                i++;
            }else if(key.equals("image")){
                String imageUrl=firebaseService.uploadFile(files[i],"images");
                resourcesList.add(key + " " + imageUrl);
                i++;
            }else if (valueElement.isJsonPrimitive() && valueElement.getAsJsonPrimitive().isString()) {
                String value = valueElement.getAsString();
                resourcesList.add(key + " " + value);
            } else {
                resourcesList.add(key + " " + valueElement);
            }
        }
        serviceClassRegister.addClass(subjectName,className,resourcesList.toString());
        redirectAttributes.addFlashAttribute("successMessage",className);
        return "redirect:/control/class";
    }

}
