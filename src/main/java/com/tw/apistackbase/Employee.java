package com.tw.apistackbase;

public class Employee {
    private String name;
    private long ID;
    private int age;
    private String gender;

    public Employee(){

    }

    public Employee(String name, int age, String gender,Long ID) {
        this.name = name;
        this.ID = ID;
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