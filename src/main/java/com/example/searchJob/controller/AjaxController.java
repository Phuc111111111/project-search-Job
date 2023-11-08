package com.example.asm2.controller;

import com.example.asm2.entity.*;
import com.example.asm2.service.*;
import com.example.asm2.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequestMapping("/ajax")
public class AjaxController {
    private RecruitmentService recruitmentService;
    private UserService userService;
    private ApplyPostService applyPostService;
    private CvService cvService;
    private SaveJobService saveJobService;
    private FollowCompanyService followCompanyService;
    private CompanyService companyService;

    @Autowired
    public AjaxController(RecruitmentService recruitmentService, UserService userService,
                          ApplyPostService applyPostService, CvService cvService, SaveJobService saveJobService,
                          FollowCompanyService followCompanyService, CompanyService companyService) {
        this.recruitmentService = recruitmentService;
        this.userService = userService;
        this.applyPostService = applyPostService;
        this.cvService = cvService;
        this.saveJobService = saveJobService;
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
        SaveJob saveJob = saveJobService.findByRecruitmentIdAndUserId(recruitmentId,user.getId());
        if (saveJob == null) {
            Recruitment recruitment = recruitmentService.findById(recruitmentId);
            saveJobService.save(new SaveJob(recruitment, user));
            return "true";
        } else {
            saveJobService.delete(saveJob);
            return "false";
        }
    }

    @PostMapping("/delete-savedJob")
    @ResponseBody
    public String deleteSavedJob(@RequestParam("savedJobId") Integer savedJobId) {
        saveJobService.deleteById(savedJobId);
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
