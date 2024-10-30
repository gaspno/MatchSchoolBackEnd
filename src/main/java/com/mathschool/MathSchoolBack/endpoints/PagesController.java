package com.mathschool.MathSchoolBack.endpoints;

import com.mathschool.MathSchoolBack.model.SchoolYear;
import com.mathschool.MathSchoolBack.model.Subject;
import com.mathschool.MathSchoolBack.services.ServiceRetrieveData;
import com.mathschool.MathSchoolBack.services.ServiceYearAndSubjectRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
@RequestMapping("control")
public class PagesController {

    @Autowired
    private ServiceRetrieveData serviceRetrieveData;

    @GetMapping("")
    public String home(Model model){
        return "home";
    }

    @GetMapping("year")
    public String yearControl(Model model){
        return "adicionar_ano_escolar";
    }

    @GetMapping("subject")
    public String subjectControl(Model model){
        model.addAttribute("years",serviceRetrieveData.getYears().stream().map(v->new SchoolYear(v.getName())).collect(Collectors.toList()));
        return "adicionar_assunto";
    }

    @GetMapping("class")
    public String classControl(Model model){
        model.addAttribute("years",serviceRetrieveData.getYears().stream().map(v->new SchoolYear(v.getName())).collect(Collectors.toList()));
        model.addAttribute("subjects",serviceRetrieveData.getSubjects().stream().map(v->new Subject(v.getName(),new SchoolYear(v.getSchoolYearEntity().getName()))).collect(Collectors.toList()));
        return "adicionar_aula";
    }

    @GetMapping("test")
    public String testControl(Model model ){
        model.addAttribute("years",serviceRetrieveData.getYears().stream().map(v->new SchoolYear(v.getName())).collect(Collectors.toList()));
        model.addAttribute("subjects",serviceRetrieveData.getSubjects().stream().map(v->new Subject(v.getName(),new SchoolYear(v.getSchoolYearEntity().getName()))).collect(Collectors.toList()));
        return "adicionar_teste";
    }
}
