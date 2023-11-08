package com.example.asm2.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @Column(name = "email")
    private String email;

    @Column(name = "logo")
    private String logo;

    @Column(name = "name_company")
    private String nameCompany;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "status")
    private Integer status;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "company", cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Recruitment> recruitments;

    public void add(Recruitment recruitment) {
        if (recruitments==null) {
            recruitments=new ArrayList<>();
        }
        recruitments.add(recruitment);
        recruitment.setCompany(this);
    }

    public Company() {
    }

    public Company(String address, String description, String email, String logo, String nameCompany, String phoneNumber, Integer status, User user, List<Recruitment> recruitments) {
        this.address = address;
        this.description = description;
        this.email = email;
        this.logo = logo;
        this.nameCompany = nameCompany;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.user = user;
        this.recruitments = recruitments;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Recruitment> getRecruitments() {
        return recruitments;
    }

    public void setRecruitments(List<Recruitment> recruitments) {
        this.recruitments = recruitments;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", logo='" + logo + '\'' +
                ", nameCompany='" + nameCompany + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                ", user=" + user +
                ", recruitments=" + recruitments +
                '}';
    }
}
