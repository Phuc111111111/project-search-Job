package com.example.asm2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
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

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "image")
    private String image;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "status")
    private Integer status;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "verification_code")
    private String verificationCode;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE})
    private Company company;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE})
    private Cv cv;


    public User() {
    }

    public User(String address, String description, String email, String fullName, String image, String password, String phoneNumber, Integer status, Role role, boolean enabled, String verificationCode, Company company, Cv cv) {
        this.address = address;
        this.description = description;
        this.email = email;
        this.fullName = fullName;
        this.image = image;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.role = role;
        this.enabled = enabled;
        this.verificationCode = verificationCode;
        this.company = company;
        this.cv = cv;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Cv getCv() {
        return cv;
    }

    public void setCv(Cv cv) {
        this.cv = cv;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", image='" + image + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                ", role=" + role +
                ", enabled=" + enabled +
                ", verificationCode='" + verificationCode + '\'' +
                ", company=" + company +
                ", cv=" + cv +
                '}';
    }
}
