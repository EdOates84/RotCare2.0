package com.example.android.rotcare1;

public class Request {
    String sub,dis,name,uid,mobile,acceptname,acceptno,mail;
    int status,count,token;

    public Request() {
    }

    public Request(String sub, String dis, String name, String uid,String mobile,String acceptname,String acceptno,String mail, int status, int count, int token) {
        this.sub = sub;
        this.dis = dis;
        this.name = name;
        this.uid = uid;
        this.mobile = mobile;
        this.acceptname = acceptname;
        this.acceptno = acceptno;
        this.mail = mail;
        this.status = status;
        this.count = count;
        this.token = token;
    }

    public String getAcceptname() {
        return acceptname;
    }

    public void setAcceptname(String acceptname) {
        this.acceptname = acceptname;
    }

    public String getAcceptno() {
        return acceptno;
    }

    public void setAcceptno(String acceptno) {
        this.acceptno = acceptno;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }
}