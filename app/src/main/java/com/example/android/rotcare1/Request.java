package com.example.android.rotcare1;

public class Request {
    String sub,dis,name,uid,mobile,alloted_name,alloted_no;
    int status,count,token;

    public Request() {
    }

    public Request(String sub, String dis, String name, String uid,String mobile,String alloted_name,String alloted_no, int status, int count, int token) {
        this.sub = sub;
        this.dis = dis;
        this.name = name;
        this.uid = uid;
        this.mobile = mobile;
        this.alloted_name = alloted_name;
        this.alloted_no = alloted_no;
        this.status = status;
        this.count = count;
        this.token = token;
    }

    public String getAlloted_name() {
        return alloted_name;
    }

    public void setAlloted_name(String alloted_name) {
        this.alloted_name = alloted_name;
    }

    public String getAlloted_no() {
        return alloted_no;
    }

    public void setAlloted_no(String alloted_no) {
        this.alloted_no = alloted_no;
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