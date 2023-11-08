package com.example.asm2.controller;

import com.example.asm2.entity.ApplyPost;
import com.example.asm2.entity.Company;
import com.example.asm2.entity.Recruitment;
import com.example.asm2.entity.User;
import com.example.asm2.service.ApplyPostService;
import com.example.asm2.service.CompanyService;
import com.example.asm2.service.RecruitmentService;
import com.example.asm2.service.UserService;
import com.example.asm2.utils.FileUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private CompanyService companyService;
    private RecruitmentService recruitmentService;
    private ApplyPostService applyPostService;

    @Autowired
    public UserController(UserService userService, CompanyService companyService,
                          RecruitmentService recruitmentService, ApplyPostService applyPostService) {
        this.userService = userService;
        this.companyService = companyService;
        this.recruitmentService = recruitmentService;
        this.applyPostService = applyPostService;
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("userInformation", user);

        if (user.getCompany() != null) {
            model.addAttribute("companyInformation", user.getCompany());
        } else {
            model.addAttribute("companyInformation", new Company());
        }

        return "user/profile";
    }

    @PostMapping("/upload-image")
    public String uploadImage(@RequestParam("file") MultipartFile file,
                              Principal principal, HttpServletRequest request) {
        User user = userService.findByEmail(principal.getName());

        // save image in uploads directory
        String siteURL = request.getRequestURL().toString().replace(request.getServletPath(), "");
        if (user.getImage() != null) {
            FileUtils.deleteImageFile(user.getImage().replace(siteURL, ""));
        }
        FileUtils.writeImageFile(file);

        // set image for user and save to database
        String fileName = file.getOriginalFilename();
        user.setImage(siteURL+"/uploads/images/"+fileName);
        userService.saveUser(user);

        return "redirect:/user/profile";
    }

    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute("userInformation") User user) {
        userService.saveUser(user);

        return "redirect:/user/profile";
    }

    @PostMapping("/upload-logo")
    public String uploadLogoCompany(@RequestParam("file") MultipartFile file,
                              Principal principal, HttpServletRequest request) {
        User user = userService.findByEmail(principal.getName());
        Company company = null;
        if (user.getCompany()==null) {
            company = new Company();
            company.setUser(user);
        }
        else company=user.getCompany();

        // save logo in uploads directory
        String siteURL = request.getRequestURL().toString().replace(request.getServletPath(), "");
        if (company.getLogo() != null) {
            FileUtils.deleteImageFile(company.getLogo().replace(siteURL, ""));
        }
        FileUtils.writeImageFile(file);

        // set logo for company and save to database
        String fileName = file.getOriginalFilename();
        company.setLogo(siteURL+"/uploads/images/"+fileName);
        companyService.save(company);

        return "redirect:/user/profile";
    }

    @PostMapping("/update-company")
    public String updateCompany(@ModelAttribute("companyInformation") Company company) {
        companyService.save(company);

        return "redirect:/user/profile";
    }

    @RequestMapping ("/search")
    public String searchJob(@RequestParam("keySearch") String keySearch,
                            @RequestParam("typeSearch") String typeSearch,
                            HttpServletRequest request,
                            Principal principal, Model model) {

        User user = userService.findByEmail(principal.getName());

        // set current page, page size and create pageable
        String pageNumber = request.getParameter("currentPage");
        int currentPage;
        if(pageNumber == null) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(pageNumber);
        }
        Integer pageSize = 5;
        PageRequest pageable = PageRequest.of(currentPage-1, pageSize);

        // get recruitment list
        Page<Recruitment> page = null;
        if (typeSearch.equals("job")) {
            page = recruitmentService.findByTitleLikeAndPageable(keySearch, pageable);
            model.addAttribute("typeSearch", "job");
        } else if (typeSearch.equals("company")) {
            List<Integer> companyIdList = companyService.findByNameCompanyLike(keySearch);
            page = recruitmentService.findByCompanyIdListAndPageable(companyIdList, pageable);
            model.addAttribute("typeSearch", "company");
        } else if (typeSearch.equals("address")) {
            page = recruitmentService.findByAddressLikeAndPageable(keySearch, pageable);
            model.addAttribute("typeSearch", "address");
        }
        List<Recruitment> recruitments = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("recruitments", recruitments);
        model.addAttribute("keySearch", keySearch);
        model.addAttribute("user", user);

        return "user/result-search-recruitment";
    }

    @GetMapping("/detail-post/{recruitmentId}")
    public String detailPost(@PathVariable int recruitmentId, Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);

        Recruitment recruitment = recruitmentService.findById(recruitmentId);
        model.addAttribute("recruitment", recruitment);

        List<ApplyPost> applyPosts = applyPostService.findByRecruitmentId(recruitmentId);
        model.addAttribute("applyPosts", applyPosts);

        return "user/detail-post";
    }

    @GetMapping("/detail-company/{companyId}")
    public String detailCompany(@PathVariable int companyId, Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);

        Company company = companyService.findById(companyId);
        model.addAttribute("company", company);
        return "user/detail-company";
    }

}
