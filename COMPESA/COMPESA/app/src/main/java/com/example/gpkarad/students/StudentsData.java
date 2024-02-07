package com.example.gpkarad.students;

public class StudentsData {
    String name,email,mobile,year,image,key,enroll,roll,address;

    public StudentsData() {
    }

    public StudentsData(String name, String email, String mobile, String year, String image, String key, String enroll, String roll, String address) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.year = year;
        this.image = image;
        this.key = key;
        this.enroll = enroll;
        this.roll = roll;
        this.address = address;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEnroll() {
        return enroll;
    }

    public void setEnroll(String enroll) {
        this.enroll = enroll;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
