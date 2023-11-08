package com.example.asm2.controller;

import com.example.asm2.entity.*;
import com.example.asm2.service.*;
import com.example.asm2.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/candidate")
public class CandidateController {
    private UserService userService;
    private CvService cvService;
    private SaveJobService saveJobService;
    private ApplyPostService applyPostService;
    private FollowCompanyService followCompanyService;
    private CompanyService companyService;

    @Autowired
    public CandidateController(UserService userService, CvService cvService, SaveJobService saveJobService, ApplyPostService applyPostService, FollowCompanyService followCompanyService, CompanyService companyService) {
        this.userService = userService;
        this.cvService = cvService;
        this.saveJobService = saveJobService;
        this.applyPostService = applyPostService;
        this.followCompanyService = followCompanyService;
        this.companyService = companyService;
    }

    @PostMapping("/upload-cv")
    public String uploadCv(@RequestParam("file") MultipartFile file, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        Cv cv = cvService.createCvForUser(file, user);
        FileUtils.writeCvFile(file);
        cvService.save(cv);

        return "redirect:/user/profile";
    }

    @PostMapping("/deleteCv/{cvId}")
    public String deleteCv(@PathVariable("cvId") Integer cvId) {
        Cv cv = cvService.findById(cvId);
        FileUtils.deleteCvFile(cv.getFileName());
        cvService.deleteById(cvId);

        return "redirect:/user/profile";
    }

    @GetMapping("/savedJob")
    public String getSavedJob(Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        List<SaveJob> saveJobList = saveJobService.findByUserId(user.getId());
        model.addAttribute("saveJobList", saveJobList);
        return "candidate/list-save-job";
    }

    @GetMapping("/appliedPost")
    public String getAppliedPost(Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        List<ApplyPost> applyPostList = applyPostService.findByUserId(user.getId());
        model.addAttribute("applyPostList", applyPostList);
        return "candidate/list-apply-job";
    }

    @GetMapping("/followCompany")
    public String getFollowCompany(Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        List<FollowCompany> followCompanyList = followCompanyService.findByUserId(user.getId());
        model.addAttribute("followCompanyList", followCompanyList);
        return "candidate/list-follow-company";
    }

    @GetMapping("/company-post/{companyId}")
    public String getCompanyPost(@PathVariable("companyId") Integer companyId, Model model) {
        Company company = companyService.findById(companyId);
        List<Recruitment> recruitmentList = company.getRecruitments();
        model.addAttribute("recruitmentList", recruitmentList);
        model.addAttribute("company", company);
        return "candidate/list-company-post";
    }

}
