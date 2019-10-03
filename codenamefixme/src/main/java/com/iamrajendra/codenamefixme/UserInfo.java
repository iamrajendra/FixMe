package com.iamrajendra.codenamefixme;

public class UserInfo {
    private String name;
    private String mobileNo;
    private String email;

    public String getName() {
        return name;
    }

    public UserInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public UserInfo setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInfo setEmail(String email) {
        this.email = email;
        return this;
    }
}
