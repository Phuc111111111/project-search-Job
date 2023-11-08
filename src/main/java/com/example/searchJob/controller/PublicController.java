package com.example.asm2.controller;

import com.example.asm2.entity.Category;
import com.example.asm2.entity.Company;
import com.example.asm2.entity.Recruitment;
import com.example.asm2.entity.User;
import com.example.asm2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PublicController {
    private RecruitmentService recruitmentService;
    private CompanyService companyService;
    private ApplyPostService applypostService;
    private CategoryService categoryService;
    private UserService userService;

    @Autowired
    public PublicController(RecruitmentService recruitmentService, CompanyService companyService,
                            ApplyPostService applypostService, CategoryService categoryService,
                            UserService userService) {
        this.recruitmentService = recruitmentService;
        this.companyService = companyService;
        this.applypostService = applypostService;
        this.categoryService = categoryService;
        this.userService = userService;
    }
    @RequestMapping ("/")
    public String getHome() {
        return "redirect:/home";
    }

    @RequestMapping ("/home")
    public String home(Model theModel, Principal principal) {

        List<String[]> companies = getInformationOfTop5Company();
        theModel.addAttribute("companies", companies);

        List<Recruitment> recruitments = getTop5Recruitment();
        theModel.addAttribute("recruitments", recruitments);

        if (principal != null) {
            User user = userService.findByEmail(principal.getName());
            theModel.addAttribute("user", user);
        }

//        List<Category> categories = categoryService.getTop4Category();
//        theModel.addAttribute("categories", categories);

        return "public/home";
    }

    public List<String[]> getInformationOfTop5Company() {
        List<Integer> top5CompanyId = recruitmentService.getTop5CompanyId();
        List<String[]> companies = new ArrayList<>();
        for (int i=0; i<top5CompanyId.size();i++) {
            Company company = companyService.findById(top5CompanyId.get(i));
            String[] companyInformationNeeded = new String[4];
            companyInformationNeeded[0] = String.valueOf(company.getId());
            companyInformationNeeded[1] = company.getNameCompany();
            companyInformationNeeded[2] = company.getLogo();
            companyInformationNeeded[3] = String.valueOf(company.getRecruitments().size());
            companies.add(companyInformationNeeded);
        }
        return companies;
    }

    public List<Recruitment> getTop5Recruitment() {
        List<Integer> top5RecruitmentId = applypostService.getTop5RecruitmentId();
        List<Recruitment> recruitments = new ArrayList<>();
        for (int i=0; i<top5RecruitmentId.size();i++) {
            Recruitment recruitment = recruitmentService.findById(top5RecruitmentId.get(i));
            recruitments.add(recruitment);
        }
        return recruitments;
    }
}
