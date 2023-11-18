package com.example.searchJob.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.searchJob.entity.ApplyPost;
import com.example.searchJob.entity.Company;
import com.example.searchJob.entity.Cv;
import com.example.searchJob.entity.FollowCompany;
import com.example.searchJob.entity.Recruitment;
import com.example.searchJob.entity.UserRecuitment;
import com.example.searchJob.entity.User;
import com.example.searchJob.service.ApplyPostService;
import com.example.searchJob.service.CompanyService;
import com.example.searchJob.service.CvService;
import com.example.searchJob.service.FollowCompanyService;
import com.example.searchJob.service.RecruitmentService;
import com.example.searchJob.service.UserRecuimentService;
import com.example.searchJob.service.UserService;
import com.example.searchJob.utils.FileUtils;

import java.security.Principal;

@Controller
@RequestMapping("/ajax")
public class AjaxController {
    private RecruitmentService recruitmentService;
    private UserService userService;
    private ApplyPostService applyPostService;
    private CvService cvService;
    private UserRecuimentService userRecuimentService;
    private FollowCompanyService followCompanyService;
    private CompanyService companyService;

    @Autowired
    public AjaxController(RecruitmentService recruitmentService, UserService userService,
                          ApplyPostService applyPostService, CvService cvService, UserRecuimentService userRecuimentService,
                          FollowCompanyService followCompanyService, CompanyService companyService) {
        this.recruitmentService = recruitmentService;
        this.userService = userService;
        this.applyPostService = applyPostService;
        this.cvService = cvService;
        this.userRecuimentService = userRecuimentService;
        this.followCompanyService = followCompanyService;
        this.companyService = companyService;
    }

    @PostMapping("/apply-job1")
    @ResponseBody
    public String applyJob1(@RequestParam("idRe") Integer recruitmentId, @RequestParam("text") String text,
                            Principal principal) {
        User user = userService.findByEmail(principal.getName());
        // check if user applied or not
        if (applyPostService.findByRecruitmentIdAndUserId(recruitmentId, user.getId()) != null) {
            return "applied";
        }

        // if user hasn't applied yet, create a ApplyPost
        Recruitment recruitment = recruitmentService.findById(recruitmentId);
        ApplyPost applyPost = applyPostService.createApplyPost(user, recruitment, text);

        // if user has a CV, set nameCv for the applyPost
        // if user has not a CV yet, return "false"
        if (user.getCv() != null) {
            String nameCv = user.getCv().getFileName();
            applyPost.setNameCv(nameCv);
        } else {
            return "false";
        }

        applyPostService.save(applyPost);

        return "true";
    }

    @PostMapping("/apply-job2")
    @ResponseBody
    public String applyJob2(@RequestParam("idRe") Integer recruitmentId, @RequestParam("text") String text,
                            @RequestParam("file") MultipartFile file, Principal principal) {
        User user = userService.findByEmail(principal.getName());

        // check if user applied or not
        if (applyPostService.findByRecruitmentIdAndUserId(recruitmentId, user.getId()) != null) {
            return "applied";
        }

        // if user hasn't applied yet, create a ApplyPost
        Recruitment recruitment = recruitmentService.findById(recruitmentId);
        ApplyPost applyPost = applyPostService.createApplyPost(user, recruitment, text);

        // create new Cv and write Cv file to store file in directory and save Cv to database
        Cv cv = cvService.createCvForUser(file, user);
        FileUtils.writeCvFile(file);
        cvService.save(cv);

        // set nameCv and save to database
        String fileName = file.getOriginalFilename();
        applyPost.setNameCv(fileName);
        applyPostService.save(applyPost);

        return "true";
    }

    @PostMapping("/save-job")
    @ResponseBody
    public String saveJob(@RequestParam("idRe") Integer recruitmentId, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        UserRecuitment saveJob = userRecuimentService.findByRecruitmentIdAndUserId(recruitmentId,user.getId());
        if (saveJob == null) {
            Recruitment recruitment = recruitmentService.findById(recruitmentId);
            userRecuimentService.save(new UserRecuitment(recruitment, user));
            return "true";
        } else {
        	userRecuimentService.delete(saveJob);
            return "false";
        }
    }

    @PostMapping("/delete-savedJob")
    @ResponseBody
    public String deleteSavedJob(@RequestParam("savedJobId") Integer savedJobId) {
    	recruitmentService.deleteById(savedJobId);
        return "true";
    }

    @PostMapping("/delete-applyPost")
    @ResponseBody
    public String deleteApplyPost(@RequestParam("applyPostId") Integer applyPostId) {
        applyPostService.deleteById(applyPostId);
        return "true";
    }

    @PostMapping("/follow-company")
    @ResponseBody
    public String followCompany(@RequestParam("companyId") Integer companyId, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        FollowCompany followCompany = followCompanyService.findByCompanyIdAndUserId(companyId,user.getId());
        if (followCompany == null) {
            Company company = companyService.findById(companyId);
            followCompanyService.save(new FollowCompany(company, user));
            return "true";
        } else {
            followCompanyService.delete(followCompany);
            return "false";
        }
    }

    @PostMapping("/delete-followCompany")
    @ResponseBody
    public String deleteFollowCompany(@RequestParam("followCompanyId") Integer followCompanyId) {
        followCompanyService.deleteById(followCompanyId);
        return "true";
    }
}
