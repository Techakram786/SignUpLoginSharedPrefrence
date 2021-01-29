package com.techakram.signuplogin;

public class UserModel {

    String First,Last,Email;

    public UserModel(String first, String last, String email) {
        First = first;
        Last = last;
        Email = email;
    }

    public String getFirst() {
        return First;
    }

    public void setFirst(String first) {
        First = first;
    }

    public String getLast() {
        return Last;
    }

    public void setLast(String last) {
        Last = last;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
