package com.example.android.rotcare1;

public class User {
    String name,email,gender,occupation,address,Date,phone,emergency;
//    Float phone,emergency;
//    Long Date,phone,emergency;

    public User(){

    }

    public User(String name, String email, String gender, String occupation, String address, String date, String phone, String emergency) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.occupation = occupation;
        this.address = address;
        Date = date;
        this.phone = phone;
        this.emergency = emergency;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }
}
