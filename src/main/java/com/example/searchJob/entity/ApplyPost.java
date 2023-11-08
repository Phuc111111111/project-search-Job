package com.example.asm2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "applypost")
public class ApplyPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "created_at")
    private String createdAt;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name_cv")
    private String nameCv;

    @Column(name = "status")
    private Integer status;

    @Column(name = "text")
    private String text;

    public ApplyPost() {
    }

    public ApplyPost(String createdAt, Recruitment recruitment, User user, String nameCv, Integer status, String text) {
        this.createdAt = createdAt;
        this.recruitment = recruitment;
        this.user = user;
        this.nameCv = nameCv;
        this.status = status;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Recruitment getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(Recruitment recruitment) {
        this.recruitment = recruitment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNameCv() {
        return nameCv;
    }

    public void setNameCv(String nameCv) {
        this.nameCv = nameCv;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ApplyPost{" +
                "id=" + id +
                ", createdAt='" + createdAt + '\'' +
                ", recruitment=" + recruitment +
                ", user=" + user +
                ", nameCv='" + nameCv + '\'' +
                ", status=" + status +
                ", text='" + text + '\'' +
                '}';
    }
}
