package com.example.searchJob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.example.searchJob.service.UserRecuimentService;
import com.example.searchJob.service.UserService;
import com.example.searchJob.utils.FileUtils;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/candidate")
public class CandidateController {
    private UserService userService;
    private CvService cvService;
    private ApplyPostService applyPostService;
    private FollowCompanyService followCompanyService;
    private CompanyService companyService;
    
    @Autowired
    public UserRecuimentService userRecuimentService;
    
    
    @Autowired
    public CandidateController(UserService userService, CvService cvService, ApplyPostService applyPostService, FollowCompanyService followCompanyService, CompanyService companyService) {
        this.userService = userService;
        this.cvService = cvService;
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

    @GetMapping("/savedJob")//cong viec da luu
    public String getSavedJob(Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        List<UserRecuitment> userRecuitments = userRecuimentService.findByUserId(user.getId());
        model.addAttribute("saveJobList", userRecuitments);
        return "candidate/list-save-job";
    }
    
    @GetMapping("/appliedPost")
    public String getAppliedPost(Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        List<ApplyPost> applyPostList = applyPostService.findByUserId(user.getId());
        model.addAttribute("applyPostList", applyPostList);
        return "candidate/list-apply-job";
    }


    @PostMapping("/appliedPost")//cong viec ung tuyen
    public String getAppliedPost(Principal principal, Model model,@ModelAttribute("recruitment") Recruitment recruitment) {
        User user = userService.findByEmail(principal.getName());
        
        ApplyPost applyPost = new ApplyPost();
        applyPost.setUser(user);
        applyPost.setRecruitment(recruitment);
        applyPostService.save(applyPost);
        
        List<ApplyPost> applyPostList = applyPostService.findByUserId(user.getId());
        model.addAttribute("applyPostList", applyPostList);
        return "candidate/list-apply-job";
    }

    @GetMapping("/followCompany")//cong ty da theo doi
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
