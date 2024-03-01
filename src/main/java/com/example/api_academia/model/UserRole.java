package com.example.api_academia.model;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    //Contructor 
     UserRole(String role) {
        this.role = role;
    }
    
    public String getRole(){
        return role;
    }
}
