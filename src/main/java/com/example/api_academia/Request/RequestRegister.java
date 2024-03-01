package com.example.api_academia.Request;

import com.example.api_academia.model.UserRole;

public record RequestRegister(String login, String password, String email,UserRole role) {

}
