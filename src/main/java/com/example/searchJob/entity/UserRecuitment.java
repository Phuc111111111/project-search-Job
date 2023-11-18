package com.example.searchJob.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_recruitment")
public class UserRecuitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "name_cv")
    private String nameCv;

    @Column(name = "status")
    private Integer status;

    @Column(name = "text")
    private String text;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;
    
    
    
    @Column(name = "created_at")
    private String createdAt;

    public UserRecuitment() {
    }
    


    public UserRecuitment(Recruitment recruitment, User user) {
		this.recruitment = recruitment;
		this.user = user;
	}



	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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



	public String getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
    
    
}
