package com.example.android.rotcare1;

public class User {
    String name, email, gender, occupation, address, Date, phone, emergency, user;
    int count;
//    Float phone,emergency;
//    Long Date,phone,emergency;

    public User(){

    }

    public User( int count,String name, String mail, String address, String phone, String emergency, String DATE, String gender, String occupation, String USER ) {

        this.count = count;
        this.name = name;
        this.email = mail;
        this.gender = gender;
        this.occupation = occupation;
        this.address = address;
        Date = DATE;
        this.phone = phone;
        this.emergency = emergency;
        this.user = USER;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}


