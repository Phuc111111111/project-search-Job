package com.example.asm2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "number_choose")
    private Integer numberChoose;

    public Category() {
    }

    public Category(String name, Integer numberChoose) {
        this.name = name;
        this.numberChoose = numberChoose;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberChoose() {
        return numberChoose;
    }

    public void setNumberChoose(Integer numberChoose) {
        this.numberChoose = numberChoose;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberChoose=" + numberChoose +
                '}';
    }
}
