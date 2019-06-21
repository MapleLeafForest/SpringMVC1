package com.fengye.springmvc.beans;

public class User {
    private int id;
    private String username;
    private String pwd;
    private String email;
    private int age;
    private Address address;//级联属性

    public User(String username, String pwd, String email, int age) {
        this.username = username;
        this.pwd = pwd;
        this.email = email;
        this.age = age;
    }

    public User(int id, String username, String pwd, String email, int age) {
        this.id = id;
        this.username = username;
        this.pwd = pwd;
        this.email = email;
        this.age = age;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
