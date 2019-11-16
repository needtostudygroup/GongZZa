package com.dongkyoo.gongzza.vos;

import java.util.Date;

public class User {
    private String id;
    private String name;
    private String password;
    private Date birthday;
    private Date signedInAt;
    private int schoolId;
    private String email;

    public User() {
    }

    public User(String id, String name, String password, Date birthday, int schoolId, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.birthday = birthday;
        this.schoolId = schoolId;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Date getSignedInAt() {
        return signedInAt;
    }

    public void setSignedInAt(Date signedInAt) {
        this.signedInAt = signedInAt;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }
}