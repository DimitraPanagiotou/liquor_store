package com.example.liquerstore;

public class Salesman {
    private int s_id;
    private String name;
    private String surname;
    private int age;
    private String phone;

    public Salesman(int s_id, String name, String surname, int age, String phone) {
        this.s_id = s_id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phone = phone;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getS_id() {
        return s_id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }
}
