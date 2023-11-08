package com.example.asm2.controller;

import com.example.asm2.entity.Category;
import com.example.asm2.entity.Recruitment;
import com.example.asm2.entity.User;
import com.example.asm2.service.CategoryService;
import com.example.asm2.service.RecruitmentService;
import com.example.asm2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/employer")
public class EmployerController {
    private UserService userService;
    private CategoryService categoryService;
    private RecruitmentService recruitmentService;

    @Autowired
    public EmployerController(UserService userService, CategoryService categoryService, RecruitmentService recruitmentService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.recruitmentService = recruitmentService;
    }

    @GetMapping("/post-list")
    public String getPostListAndPageable(HttpServletRequest request, Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());

        String pageNumber = request.getParameter("currentPage");
        int currentPage;
        if(pageNumber == null) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(pageNumber);
        }
        Integer pageSize = 5;
        PageRequest pageable = PageRequest.of(currentPage-1, pageSize);

        Page<Recruitment> page = recruitmentService.findByCompanyIdAndPageable(user.getCompany().getId(), pageable);
        List<Recruitment> recruitmentList = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("recruitmentList", recruitmentList);

        return "employer/post-list";
    }

    @GetMapping("/post-job")
    public String postJob(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("recruitment", new Recruitment());
        return "employer/post-job";
    }

    @PostMapping("/add-recruitment")
    public String addRecruitment(@ModelAttribute("recruitment") Recruitment recruitment, Principal principal, RedirectAttributes model) {
        User user = userService.findByEmail(principal.getName());
        recruitment.setCompany(user.getCompany());
        recruitment.setCreatedAt(java.time.LocalDate.now().toString());
        recruitmentService.save(recruitment);
        model.addFlashAttribute("success", "Add Recruitment Successfully!");
        return "redirect:/employer/post-list";
    }

    @GetMapping("/edit-job")
    public String editJob(@RequestParam("recruitmentId") Integer recruitmentId, Model model) {
        Recruitment recruitment = recruitmentService.findById(recruitmentId);
        model.addAttribute("recruitment", recruitment);

        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "employer/edit-job";
    }

    @PostMapping("/edit-recruitment")
    public String editRecruitment(@ModelAttribute("recruitment") Recruitment recruitment, Principal principal, RedirectAttributes model) {
        User user = userService.findByEmail(principal.getName());
        recruitment.setCompany(user.getCompany());
        recruitmentService.save(recruitment);
        model.addFlashAttribute("success", "Edit Recruitment Successfully!");
        return "redirect:/employer/post-list";
    }

    @PostMapping("/delete-recruitment")
    public String deleteRecruitment(@RequestParam("id") Integer recruitmentId, RedirectAttributes model) {
        recruitmentService.deleteById(recruitmentId);
        model.addFlashAttribute("success", "Delete Recruitment Successfully!");
        return "redirect:/employer/post-list";
    }
}
