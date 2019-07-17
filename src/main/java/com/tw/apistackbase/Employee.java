package com.tw.apistackbase;

import javax.persistence.*;

@Entity
@Table(name = "Employee")
public class Employee {
    private String name;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long ID;
    @Column
    private int age;
    private String gender;

    public Employee(){

    }

    public Employee(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public long getID() {
        return ID;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}