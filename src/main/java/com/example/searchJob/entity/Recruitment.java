package com.example.asm2.entity;

import jakarta.persistence.*;

@Entity
@Table(name="recruitment")
public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "address")
    private String address;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "description")
    private String description;

    @Column(name = "experience")
    private String experience;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "salary")
    private String salary;

    @Column(name = "status")
    private Integer status;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private String type;

    @Column(name = "views")
    private Integer views;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "deadline")
    private String deadline;

    public Recruitment() {
    }

    public Recruitment(String address, String createdAt, String description, String experience, Integer quantity, String rank, String salary, Integer status, String title, String type, Integer views, Category category, Company company, String deadline) {
        this.address = address;
        this.createdAt = createdAt;
        this.description = description;
        this.experience = experience;
        this.quantity = quantity;
        this.salary = salary;
        this.status = status;
        this.title = title;
        this.type = type;
        this.views = views;
        this.category = category;
        this.company = company;
        this.deadline = deadline;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Recruitment{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", description='" + description + '\'' +
                ", experience='" + experience + '\'' +
                ", quantity=" + quantity +
                ", salary='" + salary + '\'' +
                ", status=" + status +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", views=" + views +
                ", category=" + category +
                ", company=" + company +
                ", deadline='" + deadline + '\'' +
                '}';
    }
}
