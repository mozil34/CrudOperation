
package com.example.crudoperation.DBOfHelper;

public class DBOfHelper {
    private String userName;
    private String email;
    private String number;
    public DBOfHelper(String userName, String email, String number) {
        this.userName = userName;
        this.email = email;
        this.number = number;
    }
    public String getUserName() {
        return userName;
    }
    public String getEmail() {
        return email;
    }
    public String getNumber() {
        return number;
    }
}